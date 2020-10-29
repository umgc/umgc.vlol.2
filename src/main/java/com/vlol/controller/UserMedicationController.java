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

import com.vlol.model.User;
import com.vlol.model.UserMedication;
import com.vlol.service.UserMedicationService;
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

/** Medication controller class. */
@Controller
public class UserMedicationController {

  @Autowired private UserMedicationService medicationService;
  @Autowired private UserService userService;

  @RequestMapping(value = {"/user/medications", "/user/medications/{id}"})
  public ModelAndView viewMedicationList(
      @PathVariable(name = "id", required = false) Long id, Model model, Principal principal) {
    ModelAndView mav = new ModelAndView("user/medications");
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    Utils.getUserData(userService, mav, user);
    model.addAttribute("medicationList", user.getMedications());
    return mav;
  }

  @RequestMapping(
      value = {"/user/save-medication", "/user/save-medication/{id}"},
      method = RequestMethod.POST)
  public String saveMedication(
      @PathVariable(name = "id", required = false) Long id,
      @Valid UserMedication medication,
      BindingResult bindingResult,
      Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    medication.setUser(user);
    if (bindingResult.hasErrors()) {
      return "redirect:/user/add-medication";
    }
    medicationService.saveMedication(medication);
    if (id == null) return "redirect:/user/medications";
    else return "redirect:/user/medications/" + id;
  }

  @RequestMapping(value = {"/user/add-medication", "/user/add-medication/{id}"})
  public ModelAndView viewAddConditionPage(
      @PathVariable(name = "id", required = false) Long id, Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, false);
    if (user == null) return new ModelAndView("redirect:/login");

    ModelAndView mav = new ModelAndView("user/add-edit-medication");
    UserMedication medication = new UserMedication();
    medication.setUser(user);
    model.addAttribute("medication", medication);
    Utils.getUserData(userService, mav, user);
    return mav;
  }

  @RequestMapping(
      value = {"/user/edit-medication/{medicationId}", "/user/edit-medication/{id}/{medicationId}"})
  public ModelAndView viewEditMedicationPage(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "medicationId") Long medicationId,
      Model model) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    ModelAndView mav = new ModelAndView("user/add-edit-medication");
    // Check if the medication belongs to the user
    Boolean found = false;
    for (UserMedication med : user.getMedications())
      if (med.getMedicationId().equals(medicationId)) found = true;
    if (!found) return new ModelAndView("redirect:/login");
    UserMedication medication = medicationService.getMedication(medicationId);
    model.addAttribute("medication", medication);
    Utils.getUserData(userService, mav, user);
    return mav;
  }

  @RequestMapping(
      value = {
        "/user/delete-medication/{medicationId}",
        "/user/delete-medication/{id}/{medicationId}"
      })
  public String deleteMedication(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "medicationId") Long medicationId) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    // Check if the medication belongs to the user
    Boolean found = false;
    for (UserMedication med : user.getMedications()) {
      if (med.getMedicationId().equals(medicationId)) found = true;
    }
    if (!found) return "redirect:/login";
    medicationService.deleteMedication(medicationId);
    if (id == null) return "redirect:/user/medications";
    else return "redirect:/user/medications/" + id;
  }
}
