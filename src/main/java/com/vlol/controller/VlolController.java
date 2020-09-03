/**
 * Application controller class.
 *
 * Java Runtime Environment (JRE) version used: 11.0.7
 * Java Development Kit (JDK) version used: 11.0.7
 *
 * Styling guide: Google Java Style Guide
 *     (https://google.github.io/styleguide/javaguide.html) and
 *     Code Conventions for the Java Programming Language (Oracle: Deprecated)
 *     (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category  vlol
 * @package controller
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.controller;

import com.vlol.model.Allergy;
import com.vlol.model.Condition;
import com.vlol.model.Medication;
import com.vlol.model.Role;
import com.vlol.model.User;
import com.vlol.service.AllergyService;
import com.vlol.service.ConditionService;
import com.vlol.service.MedicationService;
import com.vlol.service.RoleService;
import com.vlol.service.UserService;
import com.vlol.repository.RoleRepository;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Application controller class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class VlolController {

    @Autowired
    private UserService userService;

    @Autowired
    private AllergyService allergyService;

    @Autowired
    private ConditionService conditionService;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView viewLoginForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public ModelAndView viewRegistrationForm() {
        ModelAndView mav = new ModelAndView();
        User user = new User();
        user.setIsActive(Boolean.TRUE);
        user.setIsLocked(Boolean.FALSE);
        // Role userRole = roleRepository.findRoleByTitle("participant");
        // user.setRole(userRole);
        Date date = new Date();
        user.setLastLoginDate(date);
        user.setDateCreated(date);
        mav.addObject("user", user);
        mav.setViewName("registration");
        return mav;
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "This user already exists!");
        }
        if (bindingResult.hasErrors()) {
            mav.addObject("msg", "Cannot add user! Check your data.");
            mav.setViewName("registration");
        } else {
            userService.saveUser(user);
            mav.addObject("msg", "User has been registered successfully!");
            mav.addObject("user", new User());
            mav.setViewName("login");
        }
        return mav;
    }

    @RequestMapping(value = {"/menu", "/menu/{id}"}, method = RequestMethod.GET)
    public ModelAndView viewMainMenu(@PathVariable(name = "id", required=false) Long id) {
        ModelAndView mav = new ModelAndView();
        Utils.getUserName(userService, mav);
        if(id == null){
            User user = Utils.getIfAuthorizedForUser(userService);
            if(user == null) return new ModelAndView("redirect:/login");
            mav.addObject("userID", user.getUserID());
            mav.addObject("user", user);
            if(Utils.isAdmin() || Utils.isProvider()){
                mav.setViewName("menu/admin-menu");
            }else{
                mav.setViewName("menu/user-menu");
            }
        }else{
            User user = Utils.getIfAuthorizedForUser(userService, id, false);
            if(user == null) return new ModelAndView("redirect:/login");
            mav.addObject("userID", user.getUserID());
            mav.addObject("user", user);
            mav.setViewName("menu/user-menu");
        }
        return mav;
    }

    /**
     * Maps the landing page to a view
     *
     * @return The landing page view.
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView viewHomePage() {
        ModelAndView mav = new ModelAndView();
        Utils.getUserName(userService, mav);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public ModelAndView viewAboutPage() {
        ModelAndView mav = new ModelAndView();
        Utils.getUserName(userService, mav);
        mav.setViewName("about");
        return mav;
    }

    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public ModelAndView viewContactPage() {
        ModelAndView mav = new ModelAndView();
        Utils.getUserName(userService, mav);
        mav.setViewName("contact");
        return mav;
    }

    @RequestMapping(value = {"/qr-capture"}, method = RequestMethod.GET)
    public ModelAndView viewQRCapturePage() {
        ModelAndView mav = new ModelAndView();
        Utils.getUserName(userService, mav);
        mav.setViewName("qr-capture");
        return mav;
    }

    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public ModelAndView viewErrorPage() {
        ModelAndView mav = new ModelAndView();
        Utils.getUserName(userService, mav);
        mav.setViewName("error");
        return mav;
    }
}
