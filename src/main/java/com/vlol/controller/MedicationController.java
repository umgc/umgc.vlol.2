/**
 * Medication controller class.
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

import com.vlol.model.Medication;
import com.vlol.model.User;
import com.vlol.service.MedicationService;
import com.vlol.service.UserService;
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
 * Medication controller class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class MedicationController {

    @Autowired
    private MedicationService medicationService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list-medications", method = RequestMethod.GET)
    public ModelAndView viewMedicationList() {
        ModelAndView mav = new ModelAndView("admin/list-medications");
        mav = getUserName(mav);
        List<Medication> medicationList = medicationService.getAllMedications();
        mav.addObject("medicationList", medicationList);
        return mav;
    }

    @RequestMapping(value = "/add-medication", method = RequestMethod.GET)
    public ModelAndView viewAddMedicationPage() {
        ModelAndView mav = new ModelAndView("admin/add-medication");
        mav = getUserName(mav);
        Medication medication = new Medication();
        mav.addObject("medication", medication);
        return mav;
    }

    @RequestMapping(value = "/save-medication", method = RequestMethod.POST)
    public String saveMedication(@Valid Medication medication, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/add-medication";
        }
        medicationService.saveMedication(medication);
        return "redirect:/list-medications";
    }
    
    @RequestMapping(value = "/update-medication", method = RequestMethod.POST)
    public String updateMedication(@Valid Medication medication, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/edit-medication";
        }
        medicationService.saveMedication(medication);
        return "redirect:/list-medications";
    }
    @RequestMapping("/edit-medication/{id}")
    public ModelAndView viewEditMedicationPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/edit-medication");
        mav = getUserName(mav);
        Medication medication = medicationService.getMedication(id);
        mav.addObject("medication", medication);
        return mav;
    }

    @RequestMapping("/delete-medication/{id}")
    public String deleteMedication(@PathVariable(name = "id") Long id) {
        medicationService.deleteMedication(id);
        return "redirect:/list-medications";
    }

    @RequestMapping("/search-medications")
    public ModelAndView findMedicationByKeyword(@RequestParam String keyword) {
        ModelAndView mav = new ModelAndView("admin/search-medications");
        mav = getUserName(mav);
        List<Medication> result = medicationService.findMedicationByKeyword(keyword);
        mav.addObject("result", result);
        return mav;
    }

    @RequestMapping("/view-medication/{id}")
    public ModelAndView viewMedicationPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/view-medication");
        mav = getUserName(mav);
        Medication medication = medicationService.getMedication(id);
        mav.addObject("medication", medication);
        return mav;
    }
    
    private ModelAndView getUserName(ModelAndView mav) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            User user = userService.findUserByEmail(auth.getName());
            mav.addObject("userRealName", user.getFirstName() + " " + user.getLastName());
        }
        return mav;
    }
}
