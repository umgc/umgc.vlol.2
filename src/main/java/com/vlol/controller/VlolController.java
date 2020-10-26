/**
 * Application controller class.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package controller
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.controller;

import com.vlol.Mailer;
import com.vlol.model.Contact;
import com.vlol.model.Role;
import com.vlol.model.User;
import com.vlol.model.UserInfo;
import com.vlol.repository.RoleRepository;
import com.vlol.service.AllergyService;
import com.vlol.service.ConditionService;
import com.vlol.service.MedicationService;
import com.vlol.service.RoleService;
import com.vlol.service.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** Application controller class. */
@Controller
public class VlolController {

  @Autowired private UserService userService;

  @Autowired private AllergyService allergyService;

  @Autowired private ConditionService conditionService;

  @Autowired private MedicationService medicationService;

  @Autowired private RoleService roleService;

  @Autowired private RoleRepository roleRepository;

  @Autowired private Environment env;

  @Value("${spring.application.name}")
  String appName;

  @Value("${mail.smtp.supportEmail}")
  String supportEmail;

  @RequestMapping(
      value = {"/login"},
      method = RequestMethod.GET)
  public ModelAndView viewLoginForm() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("login");
    mav.addObject(
        "blockdurationmins", env.getProperty("server.vlol.failedlogin.blockdurationmins"));
    return mav;
  }
  /**
   * Show the reset password form
   *
   * @return
   */
  @RequestMapping(
      value = {"/forgot-password"},
      method = RequestMethod.GET)
  public String forgotPasswordForm() {
    return "user/forgot-password";
  }

  /**
   * When an email is entered in the form get the user and send an email Always return success
   *
   * @param email
   * @return
   */
  @RequestMapping(
      value = {"/forgot-password"},
      method = RequestMethod.POST)
  public String forgotPasswordRequest(@ModelAttribute("email") String email) {
    User user = userService.findUserByEmail(email.toLowerCase());
    if (user != null) {
      try {
        new Mailer(env).resetPassword(user);
      } catch (Exception e) {
        // Always return success
      }
    }
    return "redirect:/forgot-password?success=true";
  }
  /**
   * Reset password form for the user after they have clicked on the email link Verify JWT before
   * showing
   *
   * @param jwt
   * @return
   */
  @RequestMapping(
      value = {"/reset-password"},
      method = RequestMethod.GET)
  public ModelAndView resetPasswordView(@RequestParam("jwt") String jwt) {
    ModelAndView mav = new ModelAndView("user/reset-password");
    User user = Utils.verifyJWT(userService, jwt);
    if (user == null) {
      return new ModelAndView("redirect:/forgot-password?urlExpired=true");
    }
    mav.addObject("email", user.getEmail());
    mav.addObject("jwt", jwt);
    return mav;
  }
  /**
   * Change password on submit from the reset password form Verify JWT again before changing
   * password
   *
   * @param body
   * @param jwt
   * @return
   */
  @RequestMapping(
      value = {"/reset-password"},
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ModelAndView resetPasswordRequest(
      @RequestParam("jwt") String jwt, @RequestParam Map<String, String> body) {
    User u = Utils.verifyJWT(userService, jwt);
    if (u == null) {
      return new ModelAndView("redirect:/forgot-password");
    }
    if (body.get("password") == null) return new ModelAndView("redirect:/reset-password?error");
    u.setPassword(body.get("password"));
    userService.updateUser(u, true);
    return new ModelAndView("redirect:/login?passwordchanged=true");
  }

  @RequestMapping(
      value = {"/registration"},
      method = RequestMethod.GET)
  public ModelAndView viewRegistrationForm() {
    ModelAndView mav = new ModelAndView();
    User user = new User();
    // Role userRole = roleRepository.findRoleByTitle("participant");
    // user.setRole(userRole);
    mav.addObject("user", user);
    mav.setViewName("registration");
    return mav;
  }
  
  
  @RequestMapping(
	      value = {"/provider-registration"},
	      method = RequestMethod.GET)
	  public ModelAndView providerRegistrationForm() {
	    ModelAndView mav = new ModelAndView();
	    User user = new User();
	    UserInfo userinfo = new UserInfo();
	    // Role userRole = roleRepository.findRoleByTitle("participant");
	    // user.setRole(userRole);
	    mav.addObject("user", user);
	    mav.setViewName("provider-registration");
	    return mav;
	  }
  
  @RequestMapping(
	      value = {"/provider-registration"},
	      method = RequestMethod.POST)
	  public ModelAndView createUserProvider(@Valid User user, BindingResult bindingResult) {
	    ModelAndView mav = new ModelAndView();
	    user.setIsAccountVerified(Boolean.FALSE);
	    user.setIsEmailVerified(Boolean.FALSE);
	    user.setIsLocked(Boolean.FALSE);
	    Date date = new Date();
	    user.setLastLoginDate(date);
	    user.setDateCreated(date);
	    Role userRole = roleRepository.findRoleByTitle("provider");
	    user.setRole(userRole);

	    User userExists = userService.findUserByEmail(user.getEmail());
	    if (userExists != null) {
	      bindingResult.rejectValue("email", "error.user", "This user already exists!");
	    } else if (!userService.isValid(user).isEmpty()) {
	      mav.addObject("msg", "Cannot add user! Check your data.");
	      mav.setViewName("provider-registration");
	    } else {
	      userService.createUser(user);
	      // Send a verification email after registration
	      try {
	        new Mailer(env).verifyEmail(user);
	      } catch (Exception e) {
	        // Always return success
	      }
	      return new ModelAndView("redirect:/login?verifyEmail");
	    }
	    return mav;
	  }

  @RequestMapping(
      value = {"/registration"},
      method = RequestMethod.POST)
  public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
    ModelAndView mav = new ModelAndView();
    user.setIsAccountVerified(Boolean.TRUE);
    user.setIsEmailVerified(Boolean.FALSE);
    user.setIsLocked(Boolean.FALSE);
    Date date = new Date();
    user.setLastLoginDate(date);
    user.setDateCreated(date);
    Role userRole = roleRepository.findRoleByTitle("participant");
    user.setRole(userRole);

    User userExists = userService.findUserByEmail(user.getEmail());
    if (userExists != null) {
      bindingResult.rejectValue("email", "error.user", "This user already exists!");
    } else if (!userService.isValid(user).isEmpty()) {
      mav.addObject("msg", "Cannot add user! Check your data.");
      mav.setViewName("registration");
    } else {
      userService.createUser(user);
      // Send a verification email after registration
      try {
        new Mailer(env).verifyEmail(user);
      } catch (Exception e) {
        // Always return success
      }
      return new ModelAndView("redirect:/login?verifyEmail");
    }
    return mav;
  }
  /**
   * Check the verification email's link, if valid mark the account as verified.
   *
   * @param jwt
   * @return
   */
  @RequestMapping(
      value = {"/verify-email"},
      method = RequestMethod.GET)
  public ModelAndView verifyEmailRecieve(@RequestParam(name = "jwt", required = false) String jwt) {
    if (jwt != null) {
      User user = Utils.verifyJWT(userService, jwt);
      if (user == null) {
        return new ModelAndView("redirect:/verify-email?error");
      }
      user.setIsEmailVerified(true); // Verify email
      userService.updateUser(user);
      return new ModelAndView("redirect:/verify-email?success");
    }
    return new ModelAndView("user/verify-email");
  }
  /**
   * Send verification email, used for resending
   *
   * @param model
   * @return
   */
  @RequestMapping(
      value = {"/verify-email"},
      method = RequestMethod.POST)
  public ModelAndView verifyEmail(Model model) {
    String email = (String) model.getAttribute("email");
    if (email != null) {
      // Re-send a verification email
      try {
        new Mailer(env).verifyEmail(userService.findUserByEmail(email));
      } catch (Exception e) {
        return new ModelAndView("redirect:/verify-email?erroremail");
      }
    } else return new ModelAndView("redirect:/verify-email?error");
    return new ModelAndView("redirect:/verify-email?success");
  }

  @RequestMapping(
      value = {"/menu", "/menu/{id}"},
      method = RequestMethod.GET)
  public ModelAndView viewMainMenu(
      @PathVariable(name = "id", required = false) Long id, Principal principal) {
    ModelAndView mav = new ModelAndView();
    User user;
    if (id == null) {
      user = Utils.getIfAuthorizedForUser(userService);
      if (!user.getIsEmailVerified()) {
        return new ModelAndView("redirect:/verify-email");
      } else if (Utils.isAdmin()) {
        mav.addObject("userAlert", userService.getNewProviders().size() > 0);
        mav.setViewName("menu/admin-menu");
      } else if (Utils.isProvider()) {
    	if(!user.getIsAccountVerified()) {
    		mav.setViewName("access-denied");
    	}else {
    		mav.setViewName("menu/admin-menu");
    	}
      } else {
        mav.setViewName("menu/user-menu");
      }
    } else {
      user = Utils.getIfAuthorizedForUser(userService, id, false);
      // If the requested user is a user
      if (user.getRole().getRoleId() == 1) mav.setViewName("menu/user-menu");
      else mav.setViewName("menu/admin-menu");
    }
    if (user == null) return new ModelAndView("redirect:/login");
    Utils.getUserData(userService, mav, user);
    mav.addObject("user", user);
    // Check if this participant is authorized for other accounts, and if on the current page
    if (Utils.isParticipant() && user.getEmail().equals(principal.getName())) {
      mav.addObject(
          "hasAuthorizingUsers",
          userService.findAuthorizingUsers(user.getEmail().toLowerCase()).size() > 0);
    }
    return mav;
  }

  /**
   * Maps the landing page to a view
   *
   * @return The landing page view.
   */
  @RequestMapping(
      value = {"/", "/index"},
      method = RequestMethod.GET)
  public ModelAndView viewHomePage() {
    ModelAndView mav = new ModelAndView();
    Utils.getUserData(userService, mav);
    mav.setViewName("index");
    return mav;
  }

  @RequestMapping(
      value = {"/about"},
      method = RequestMethod.GET)
  public ModelAndView viewAboutPage() {
    ModelAndView mav = new ModelAndView();
    Utils.getUserData(userService, mav);
    mav.setViewName("about");
    return mav;
  }

  @RequestMapping(
      value = {"/contact"},
      method = RequestMethod.GET)
  public ModelAndView viewContactPage() {
    ModelAndView mav = new ModelAndView();
    Utils.getUserData(userService, mav);
    mav.setViewName("contact");
    mav.addObject("contact", new Contact());
    return mav;
  }

  @RequestMapping(
      value = {"/contact"},
      method = RequestMethod.POST)
  public ModelAndView sentRequestContact(@Valid Contact contact, BindingResult bindingResult) {
    ModelAndView mav = new ModelAndView();
    try {
      new Mailer(env).sendContact(contact);
    } catch (Exception e) {
      // Always return success
    }
    mav.setViewName("contact");
    mav.addObject("contact", new Contact());
    return mav;
  }

  @RequestMapping(
      value = {"/qr-capture"},
      method = RequestMethod.GET)
  public ModelAndView viewQRCapturePage() {
    ModelAndView mav = new ModelAndView();
    Utils.getUserData(userService, mav);
    mav.setViewName("qr-capture");
    return mav;
  }

  @RequestMapping(
      value = {"/error"},
      method = RequestMethod.GET)
  public ModelAndView viewErrorPage() {
    ModelAndView mav = new ModelAndView();
    Utils.getUserData(userService, mav);
    mav.setViewName("error");
    mav.addObject("supportEmail", supportEmail);
    return mav;
  }

  @RequestMapping(
      value = {"/expired-qrcode"},
      method = RequestMethod.GET)
  public ModelAndView viewExpiredQrCodePage() {
    ModelAndView mav = new ModelAndView();
    Utils.getUserData(userService, mav);
    return mav;
  }
}
