/**
 * Allergy controller class.
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

import com.vlol.model.Allergy;
import com.vlol.service.AllergyService;
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
 * Allergy controller class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class AllergyController {
    
    @Autowired
    private AllergyService allergyService;
    
    @RequestMapping("/list-allergies")
    public String viewAllergyList(Model model) {
        List<Allergy> allergyList = allergyService.listAllAllergies();
        model.addAttribute("allergyList", allergyList);
        return "admin/list-allergies";
    }

    @RequestMapping("/add-allergy")
    public String viewAddAllergyPage(Model model) {
        Allergy allergy = new Allergy();
        model.addAttribute("allergy", allergy);
        return "admin/add-allergy";
    }

    @RequestMapping(value = "/save-allergy", method = RequestMethod.POST)
    public String saveAllergy(@ModelAttribute("allergy") Allergy allergy) {
        allergyService.saveAllergy(allergy);
        return "redirect:/list-allergies";
    }

    @RequestMapping("/edit-allergy/{id}")
    public ModelAndView viewEditAllergyPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/edit-allergy");
        Allergy allergy = allergyService.getAllergy(id);
        mav.addObject("allergy", allergy);
        return mav;
    }

    @RequestMapping("/delete-allergy/{id}")
    public String deleteAllergy(@PathVariable(name = "id") int id) {
        allergyService.deleteAllergy(id);
        return "redirect:/list-allergies";
    }

    @RequestMapping("/search-allergies")
    public ModelAndView search(@RequestParam String keyword) {
        List<Allergy> result = allergyService.searchForAllergy(keyword);
        ModelAndView mav = new ModelAndView("admin/search-allergies");
        mav.addObject("result", result);
        return mav;
    }
}
