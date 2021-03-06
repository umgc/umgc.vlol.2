/**
 * User controller class.
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
import com.vlol.model.Role;
import com.vlol.model.User;
import com.vlol.model.UserInfo;
import com.vlol.service.RoleService;
import com.vlol.service.UserAllergyService;
import com.vlol.service.UserConditionService;
import com.vlol.service.UserMedicationService;
import com.vlol.service.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** User controller class. */
@Controller
public class UserControlller {

  @Autowired private Environment env;

  @Autowired private UserService userService;

  @Autowired private UserAllergyService allergyService;

  @Autowired private UserConditionService conditionService;

  @Autowired private UserMedicationService medicationService;

  @Autowired private RoleService roleService;

  @RequestMapping("/list-users")
  public ModelAndView viewUserList(Principal principal) {
    ModelAndView mav = new ModelAndView("admin/list-users");
    Utils.getUserData(userService, mav);
    List<User> userList;
    if (Utils.isAdmin()) userList = userService.getAllUsers();
    else if (Utils.isProvider()) userList = userService.getAllParticipants();
    else if (Utils.isParticipant()) {
      userList = userService.findAuthorizingUsers(principal.getName().toLowerCase());
    } else return new ModelAndView("redirect:/login");
    mav.addObject("userList", userList);
    return mav;
  }

  @RequestMapping("/admin/add-user")
  public ModelAndView viewAddUserPage() {
    if (!Utils.isAdmin()) return new ModelAndView("redirect:/login");
    User user = new User();
    ModelAndView mav = new ModelAndView("admin/add-user");
    Utils.getUserData(userService, mav);
    mav.addObject("user", user);
    user.setIsAccountVerified(Boolean.TRUE);
    user.setIsEmailVerified(Boolean.TRUE);
    List<Role> roles = roleService.getAllRoles();
    user.setRole(roles.get(0));
    mav.addObject("roles", roles);
    return mav;
  }

  @RequestMapping(value = "/admin/create-user", method = RequestMethod.POST)
  public String saveUser(@ModelAttribute("user") User user) {
    Date date = new Date();
    user.setLastLoginDate(date);
    user.setDateCreated(date);
    if (!Utils.isAdmin()) return "redirect:/login";
    userService.createUser(user);
    return "redirect:/list-users";
  }

  @RequestMapping(value = {"/user/account", "/user/account/{id}"})
  public ModelAndView viewAccountPage(@PathVariable(name = "id", required = false) Long id) {
    ModelAndView mav = new ModelAndView("user/edit-account");
    User user = Utils.getIfUserOrAdmin(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    Utils.getUserData(userService, mav, user);
    mav.addObject("user", user);
    List<Role> roles = roleService.getAllRoles();
    mav.addObject("roles", roles);
    return mav;
  }

  @RequestMapping(
      value = {"/user/account", "/user/account/{id}"},
      method = RequestMethod.POST)
  public String updateAccountPage(
      User changedUser, @PathVariable(name = "id", required = false) Long id) {
    ModelAndView mav = new ModelAndView("user/edit-account");
    User user = Utils.getIfUserOrAdmin(userService, id, true);
    if (user == null) return "redirect:/login";
    // Copy any fields that can change to the old user
    user.setFirstName(changedUser.getFirstName());
    user.setLastName(changedUser.getLastName());
    Boolean emailChange = false;
    // If email change unverify the account
    if (!changedUser.getEmail().equals(user.getEmail())) {
      user.setIsEmailVerified(false);
      emailChange = true;
    }
    user.setEmail(changedUser.getEmail());
    Boolean passwordChange = false;
    if (!user.getPassword().isBlank()) {
      user.setPassword(changedUser.getPassword());
      passwordChange = true;
      user.setIsLocked(false);
    }
    if (Utils.isAdmin()) {
      // If the admin unverified a previously verified account.
      if (user.getIsEmailVerified() && !changedUser.getIsEmailVerified()) {
        try {
          new Mailer(env).verifyEmail(user);
        } catch (Exception e) {
          // Always return success
        }
      }
      user.setAdminComments(changedUser.getAdminComments());
      user.setIsAccountVerified(changedUser.getIsAccountVerified());
      user.setIsLocked(changedUser.getIsLocked());
      user.setIsEmailVerified(changedUser.getIsEmailVerified());
      user.setRole(changedUser.getRole());
    } else {
      if (emailChange) {
        try {
          new Mailer(env).verifyEmail(user);
        } catch (Exception e) {
          // Always return success
        }
      }
    }
    userService.updateUser(user, passwordChange);
    if (emailChange) return "redirect:/login";
    else if (id != null) return "redirect:/user/account/" + id;
    else return "redirect:/user/account";
  }

  @RequestMapping(value = {"/user/profile", "/user/profile/{id}"})
  public ModelAndView viewProfilePage(@PathVariable(name = "id", required = false) Long id) {
    ModelAndView mav = new ModelAndView("user/edit-profile");
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    mav.addObject("userInfo", user.getUserInfo());
    Utils.getUserData(userService, mav, user);
    return mav;
  }

  @RequestMapping(
      value = {"/user/profile", "/user/profile/{id}"},
      method = RequestMethod.POST)
  public String updateProfilePage(
      UserInfo changedUser, @PathVariable(name = "id", required = false) Long id) {
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    // All fields come back from form except user and user info id
    changedUser.setUser(user);
    changedUser.setInfoId(user.getUserInfo().getInfoId());
    userService.updateUserInfo(changedUser);
    if (id != null) return "redirect:/user/profile/" + id;
    else return "redirect:/user/profile";
  }

  @RequestMapping("/user/delete/{id}")
  public String deleteUser(@PathVariable(name = "id") Long id) {
    User user = Utils.getIfUserOrAdmin(userService, id, true);
    if (user == null) return "redirect:/login";
    userService.delete(user);
    if (Utils.isUser(user)) { // If the user deletes the account log them out
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      auth.setAuthenticated(false);
      return "redirect:/login";
    } else { // If its the admin bring them back to the user page
      return "redirect:/list-users";
    }
  }

  @RequestMapping("/admin/search-users")
  public ModelAndView findUserByKeyword(@RequestParam String keyword) {
    if (!Utils.isAdmin() && !Utils.isProvider()) {
      return new ModelAndView("redirect:/login");
    }
    List<User> result = userService.findUserByKeyword(keyword);
    ModelAndView mav = new ModelAndView("admin/search-users");
    Utils.getUserData(userService, mav);
    mav.addObject("result", result);
    return mav;
  }

  @RequestMapping(value = {"/user/view/{id}", "/user/view/{id}/{jwt}/{code}"})
  public ModelAndView viewUserPage(
      @PathVariable(name = "id") Long id,
      @PathVariable(name = "jwt", required = false) String jwt,
      @PathVariable(name = "code", required = false) String code) {

    ModelAndView mav = new ModelAndView("user/view-user");
    User user;
    if (jwt != null) {
      if (env.getProperty("qrCode.onlyByRegistered") != null
          && env.getProperty("qrCode.onlyByRegistered").equals("true")) {
        if (!Utils.isAdmin() && !Utils.isProvider()) {
          return new ModelAndView("redirect:/login");
        }
      }
      user = userService.getUser(id);
      if (!Utils.verifyJWT(user, jwt)) { // Check jwt verification
        return new ModelAndView("redirect:/login");
      } else {
        // parse the jwt to check if  and return the proper redirect
        if (!Utils.verifyQRCode(user, code)) {
          return new ModelAndView("redirect:/expired-qrcode");
        }
      }
    } else { // If just an id check if
      user = Utils.getIfAuthorizedForUser(userService, id, false);
      if (user == null) return new ModelAndView("redirect:/login");
    }

    Utils.getUserData(userService, mav, user);
    mav.addObject("user", user);
    List<Role> roles = roleService.getAllRoles();
    mav.addObject("roles", roles);

    return mav;
  }
}
