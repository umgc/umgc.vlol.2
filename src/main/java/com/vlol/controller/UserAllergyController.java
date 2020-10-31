/**
 * Medical allergy controller class.
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
 */
package com.vlol.controller;

import com.vlol.model.User;
import com.vlol.model.UserAllergy;
import com.vlol.service.UserAllergyService;
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

/** Medical allergy controller class. */
@Controller
public class UserAllergyController {

  @Autowired private UserAllergyService userAllergyService;
  @Autowired private UserService userService;

  @RequestMapping(value = {"/user/allergies", "/user/allergies/{id}"})
  public ModelAndView viewAllergyList(
      @PathVariable(name = "id", required = false) Long id, Model model, Principal principal) {
    ModelAndView mav = new ModelAndView("user/allergies");
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    Utils.getUserData(userService, mav, user);
    model.addAttribute("allergyList", user.getAllergies());
    return mav;
  }

  @RequestMapping(
      value = {"/user/save-allergy", "/user/save-allergy/{id}"},
      method = RequestMethod.POST)
  public String saveAllergy(
      @PathVariable(name = "id", required = false) Long id,
      @Valid UserAllergy allergy,
      BindingResult bindingResult,
      Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    allergy.setUser(user);
    if (bindingResult.hasErrors()) {
      return "redirect:/user/add-allergy";
    }
    userAllergyService.saveAllergy(allergy);
    if (id == null) return "redirect:/user/allergies";
    else return "redirect:/user/allergies/" + id;
  }

  @RequestMapping(value = {"/user/add-allergy", "/user/add-allergy/{id}"})
  public ModelAndView viewAddAllergyPage(
      @PathVariable(name = "id", required = false) Long id, Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, false);
    if (user == null) return new ModelAndView("redirect:/login");

    ModelAndView mav = new ModelAndView("user/add-edit-allergy");
    UserAllergy allergy = new UserAllergy();
    allergy.setUser(user);
    model.addAttribute("allergy", allergy);
    Utils.getUserData(userService, mav, user);
    return mav;
  }

  @RequestMapping(value = {"/user/edit-allergy/{allergyId}", "/user/edit-allergy/{id}/{allergyId}"})
  public ModelAndView viewEditAllergyPage(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "allergyId") Long allergyId,
      Model model) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    ModelAndView mav = new ModelAndView("user/add-edit-allergy");
    // Check if the allergy belongs to the user
    Boolean found = false;
    for (UserAllergy allergy : user.getAllergies())
      if (allergy.getAllergyId().equals(allergyId)) found = true;
    if (!found) return new ModelAndView("redirect:/login");
    UserAllergy allergy = userAllergyService.getAllergy(allergyId);
    model.addAttribute("allergy", allergy);
    Utils.getUserData(userService, mav, user);
    return mav;
  }

  @RequestMapping(
      value = {"/user/delete-allergy/{allergyId}", "/user/delete-allergy/{id}/{allergyId}"})
  public String deleteAllergy(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "allergyId") Long allergyId) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    // Check if the allergy belongs to the user
    Boolean found = false;
    for (UserAllergy allergy : user.getAllergies()) {
      if (allergy.getAllergyId().equals(allergyId)) found = true;
    }
    if (!found) return "redirect:/login";
    userAllergyService.deleteAllergy(allergyId);
    if (id == null) return "redirect:/user/allergies";
    else return "redirect:/user/allergies/" + id;
  }
}
