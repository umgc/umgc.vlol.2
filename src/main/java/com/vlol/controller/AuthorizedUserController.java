/**
 * Role controller class.
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

import com.vlol.model.Role;
import com.vlol.model.User;
import com.vlol.service.RoleService;
import com.vlol.service.UserService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Role controller class.
 *
 * @author Michael Marcucci <marcuccm@student.umgc.edu>
 */
@Controller
public class AuthorizedUserController {
    
    @Autowired
    private UserService userService;

//    @Autowired
    @RequestMapping(value = "/authorized-user/{id}", method = RequestMethod.GET)
    public ModelAndView viewAuthorizedUserList(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/authorized-user");
        Utils.getUserName(userService, mav);
        User user = Utils.getIfAuthorizedForUser(userService, id, false);
        mav.addObject("users", user!=null?user.getAuthorizedEmails():null);
        mav.addObject("id", user!=null?user.getUserID():null);
        return mav;
    }

    @RequestMapping(value = "/add-authorized-user", method = RequestMethod.POST)
    public String addAuthorizedUser() {
        return "redirect:/authorized-user";
    }
    
    @RequestMapping(value = "/delete-authorized-user/{id}/{email}", method = RequestMethod.DELETE)
    public String deleteAuthorizedUser(@PathVariable(name = "id") Long id, @PathVariable(name = "email") Long email) {
        return "redirect:/authorized-user";
    }
}
