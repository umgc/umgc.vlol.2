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
 * @package controller
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.controller;

import com.vlol.model.Allergy;
import com.vlol.model.User;
import com.vlol.service.AllergyService;
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
 * Allergy controller class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class AllergyController {

    @Autowired
    private UserService userService;

    @Autowired
    private AllergyService allergyService;
    
    @RequestMapping(value = "/list-allergies", method = RequestMethod.GET)
    public ModelAndView viewAllergyList() {
        ModelAndView mav = new ModelAndView("admin/list-allergies");
        mav = getUserName(mav);
        List<Allergy> allergyList = allergyService.getAllAllergies();
        mav.addObject("allergyList", allergyList);
        return mav;
    }

    @RequestMapping(value = "/add-allergy", method = RequestMethod.GET)
    public ModelAndView viewAddAllergyPage() {
        ModelAndView mav = new ModelAndView("admin/add-allergy");
        mav = getUserName(mav);
        Allergy allergy = new Allergy();
        mav.addObject("allergy", allergy);
        return mav;
    }
    
    @RequestMapping(value = "/save-allergy", method = RequestMethod.POST)
    public String saveAllergy(@Valid Allergy allergy, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/add-allergy";
        }
        allergyService.saveAllergy(allergy);
        return "redirect:/list-allergies";
    }

    @RequestMapping(value = "/update-allergy", method = RequestMethod.POST)
    public String updateAllergy(@Valid Allergy allergy, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/edit-allergy";
        }
        allergyService.saveAllergy(allergy);
        return "redirect:/list-allergies";
    }

    @RequestMapping(value = "/edit-allergy/{id}", method = RequestMethod.GET)
    public ModelAndView viewEditAllergyPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/edit-allergy");
        mav = getUserName(mav);
        Allergy allergy = allergyService.getAllergy(id);
        mav.addObject("allergy", allergy);
        return mav;
    }

    @RequestMapping(value = "/delete-allergy/{id}", method = RequestMethod.GET)
    public String deleteAllergy(@PathVariable(name = "id") Long id) {
        allergyService.deleteAllergy(id);
        return "redirect:/list-allergies";
    }

    @RequestMapping(value = "/search-allergies", method = RequestMethod.GET)
    public ModelAndView findAllergyByKeyword(@RequestParam String keyword) {
        ModelAndView mav = new ModelAndView("admin/search-allergies");
        mav = getUserName(mav);
        List<Allergy> result = allergyService.findAllergyByKeyword(keyword);
        mav.addObject("result", result);
        return mav;
    }

    @RequestMapping(value = "/view-allergy/{id}", method = RequestMethod.GET)
    public ModelAndView viewAllergyPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/view-allergy");
        mav = getUserName(mav);
        Allergy allergy = allergyService.getAllergy(id);
        mav.addObject("allergy", allergy);
        return mav;
    }

    private ModelAndView getUserName(ModelAndView mav) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            User user = userService.findUserByUsername(auth.getName());
            mav.addObject("userRealName", user.getFirstName() + " " + user.getLastName());
        }
        return mav;
    }
}
