/**
 * Medication controller class.
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

import com.vlol.model.AuthorizedUser;
import com.vlol.model.User;
import com.vlol.service.AuthorizedUserService;
import com.vlol.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Authorized User controller class.
 *
 * @author Michael Marcucci
 */
@Controller
public class AuthorizedUserController {

  @Autowired private AuthorizedUserService authorizedUserService;
  @Autowired private UserService userService;

  @RequestMapping(value = {"/user/authorized-user", "/user/authorized-user/{id}"})
  public ModelAndView viewAuthorizedUserList(
      @PathVariable(name = "id", required = false) Long id, Model model, Principal principal) {
    ModelAndView mav = new ModelAndView("user/authorized-user");
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    Utils.getUserData(userService, mav, user.getUserId());
    model.addAttribute("authorizedUserList", user.getAuthorizedEmails());
    return mav;
  }

  @RequestMapping(
      value = {"/user/save-authorized-user", "/user/save-authorized-user/{id}"},
      method = RequestMethod.POST)
  public String saveAuthorizedUser(
      @PathVariable(name = "id", required = false) Long id,
      @Valid AuthorizedUser authorizedUser,
      BindingResult bindingResult,
      Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    authorizedUser.setUser(user);
    // Lower Case the email for better matching
    authorizedUser.setAuthorizedEmail(authorizedUser.getAuthorizedEmail().toLowerCase());
    if (bindingResult.hasErrors()) {
      return "redirect:/user/add-authorized-user";
    }
    authorizedUserService.saveAuthorizedUser(authorizedUser);
    return "redirect:/user/authorized-user";
  }

  @RequestMapping(value = {"/user/add-authorized-user", "/user/add-authorized-user/{id}"})
  public ModelAndView viewAddConditionPage(
      @PathVariable(name = "id", required = false) Long id, Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, false);
    if (user == null) return new ModelAndView("redirect:/login");

    ModelAndView mav = new ModelAndView("user/add-edit-authorized-user");
    AuthorizedUser authorizedUser = new AuthorizedUser();
    authorizedUser.setUser(user);
    model.addAttribute("authorizedUser", authorizedUser);
    Utils.getUserData(userService, mav, user.getUserId());
    return mav;
  }

  @RequestMapping(
      value = {
        "/user/edit-authorized-user/{authorizedUserId}",
        "/user/edit-authorized-user/{id}/{authorizedUserId}"
      })
  public ModelAndView viewEditAuthorizedUserPage(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "authorizedUserId") Long authorizedUserId,
      Model model) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    ModelAndView mav = new ModelAndView("user/add-edit-authorized-user");
    // Check if the authorizedUser belongs to the user
    Boolean found = false;
    for (AuthorizedUser au : user.getAuthorizedEmails())
      if (au.getAuthorizedUserId().equals(authorizedUserId)) found = true;
    if (!found) return new ModelAndView("redirect:/login");
    AuthorizedUser authorizedUser = authorizedUserService.getAuthorizedUser(authorizedUserId);
    model.addAttribute("authorizedUser", authorizedUser);
    Utils.getUserData(userService, mav, user.getUserId());
    return mav;
  }

  @RequestMapping(
      value = {
        "/user/delete-authorized-user/{authorizedUserId}",
        "/user/delete-authorized-user/{id}/{authorizedUserId}"
      })
  public String deleteAuthorizedUser(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "authorizedUserId") Long authorizedUserId) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    // Check if the authorizedUser belongs to the user
    Boolean found = false;
    for (AuthorizedUser au : user.getAuthorizedEmails()) {
      if (au.getAuthorizedUserId().equals(authorizedUserId)) found = true;
    }
    if (!found) return "redirect:/login";
    authorizedUserService.deleteAuthorizedUser(authorizedUserId);
    if (id == null) return "redirect:/user/authorized-user";
    else return "redirect:/user/authorized-user/" + id;
  }
}
