/**
 * Medical vaccine controller class.
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
 */
package com.vlol.controller;

import com.vlol.model.User;
import com.vlol.model.UserVaccine;
import com.vlol.service.UserVaccineService;
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
 * Medical vaccine controller class.
 */
@Controller
public class UserVaccineController {

    @Autowired
    private UserVaccineService userVaccineService;
    @Autowired
    private UserService userService;

    @RequestMapping( value = {"/user/vaccines", "/user/vaccines/{id}"})
    public ModelAndView viewVaccineList(@PathVariable(name = "id", required=false) Long id, Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("user/vaccines");
        Utils.getUserName(userService, mav);
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return new ModelAndView("redirect:/login");
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("vaccineList", user.getVaccines());
        return mav;
    }

    @RequestMapping(value = {"/user/save-vaccine", "/user/save-vaccine/{id}"}, method = RequestMethod.POST)
    public String saveVaccine(@PathVariable(name = "id", required=false) Long id, @Valid UserVaccine vaccine, BindingResult bindingResult, Model model) {
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return "redirect:/login";
        vaccine.setUser(user);
        if (bindingResult.hasErrors()) {
            return "redirect:/user/add-vaccine";
        }
        userVaccineService.saveVaccine(vaccine);
        return "redirect:/user/vaccines";
    }
    
    @RequestMapping(value = {"/user/add-vaccine", "/user/add-vaccine/{id}"})
    public ModelAndView viewAddVaccinePage(@PathVariable(name = "id", required=false) Long id, Model model) {
        User user = Utils.getIfAuthorizedForUser(userService, id, false);
        if(user == null) return new ModelAndView("redirect:/login");
        
        ModelAndView mav = new ModelAndView("user/add-edit-vaccine");
        Utils.getUserName(userService, mav);
        UserVaccine vaccine = new UserVaccine();
        vaccine.setUser(user);
        model.addAttribute("vaccine", vaccine);
        model.addAttribute("userId", user.getUserId());
        return mav;
    }
    @RequestMapping(value = {"/user/edit-vaccine/{vaccineId}", "/user/edit-vaccine/{id}/{vaccineId}"})
    public ModelAndView viewEditVaccinePage(@PathVariable(name = "id", required=false) Long id, @PathVariable(name = "vaccineId") Long vaccineId, Model model) {
        // Check if this user can edit the requested user
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return new ModelAndView("redirect:/login");
        ModelAndView mav = new ModelAndView("user/add-edit-vaccine");
        Utils.getUserName(userService, mav);
        // Check if the vaccine belongs to the user
        Boolean found = false;
        for(UserVaccine vaccine : user.getVaccines())
            if(vaccine.getVaccineId().equals(vaccineId))
                found = true;
        if(!found)
            return new ModelAndView("redirect:/login");
        UserVaccine vaccine = userVaccineService.getVaccine(vaccineId);
        model.addAttribute("vaccine", vaccine);
        model.addAttribute("userId", user.getUserId());
        return mav;
    }

    @RequestMapping(value = {"/user/delete-vaccine/{vaccineId}", "/user/delete-vaccine/{id}/{vaccineId}"})
    public String deleteVaccine(@PathVariable(name = "id", required=false) Long id, @PathVariable(name = "vaccineId") Long vaccineId) {
        // Check if this user can edit the requested user
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return "redirect:/login";
        // Check if the vaccine belongs to the user
        Boolean found = false;
        for(UserVaccine vaccine : user.getVaccines()){
            if(vaccine.getVaccineId().equals(vaccineId))
                found = true;
        }
        if(!found)
            return "redirect:/login";
        userVaccineService.deleteVaccine(vaccineId);
        if(id==null)
            return "redirect:/user/vaccines";
        else
            return "redirect:/user/vaccines/"+id;
    }
}
