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
 * @package configuration
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.controller;

import com.vlol.model.Medication;
import com.vlol.service.MedicationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping("/list-medications")
    public String viewMedicationList(Model model) {
        List<Medication> medicationList = medicationService.listAllMedications();
        model.addAttribute("medicationList", medicationList);
        return "/admin/list-medications";
    }

    @RequestMapping("/add-medication")
    public String viewAddMedicationPage(Model model) {
        Medication medication = new Medication();
        model.addAttribute("medication", medication);
        return "/admin/add-medication";
    }

    @RequestMapping(value = "/save-medication", method = RequestMethod.POST)
    public String saveMedication(@ModelAttribute("medication") Medication medication) {
        medicationService.saveMedication(medication);
        return "redirect:/list-medications";
    }

    @RequestMapping("/edit-medication/{id}")
    public ModelAndView viewEditMedicationPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/edit-medication");
        Medication medication = medicationService.getMedication(id);
        mav.addObject("medication", medication);
        return mav;
    }

    @RequestMapping("/delete-medication/{id}")
    public String deleteMedication(@PathVariable(name = "id") int id) {
        medicationService.deleteMedication(id);
        return "redirect:/list-medications";
    }

    @RequestMapping("/search-medications")
    public ModelAndView search(@RequestParam String keyword) {
        List<Medication> result = medicationService.searchForMedication(keyword);
        ModelAndView mav = new ModelAndView("admin/search-medications");
        mav.addObject("result", result);
        return mav;
    }
}
