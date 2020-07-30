/**
 * User controller class.
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
 * User controller class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class UserControlller {

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

    @RequestMapping("/list-users")
    public String viewUserList(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin/list-users";
    }

    @RequestMapping("/add-user")
    public ModelAndView viewAddUserPage() {
        User user = new User();
        ModelAndView mav = new ModelAndView("admin/add-user");
        mav.addObject("user", user);
        List<Allergy> allergies = allergyService.getAllAllergies();
        mav.addObject("allergies", allergies);
        List<Condition> conditions = conditionService.getAllConditions();
        mav.addObject("conditions", conditions);
        List<Medication> medications = medicationService.getAllMedications();
        mav.addObject("medications", medications);
        List<Role> roles = roleService.getAllRoles();
        mav.addObject("roles", roles);
        List<User> agents = userService.getAllUsers();
        mav.addObject("agents", agents);
        return mav;
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/list-users";
    }

    @RequestMapping(value = "/update-user", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/list-users";
    }

    @RequestMapping("/edit-user/{id}")
    public ModelAndView viewEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/edit-user");
        User user = userService.getUser(id);
        mav.addObject("user", user);
        List<Allergy> allergies = allergyService.getAllAllergies();
        mav.addObject("allergies", allergies);
        List<Condition> conditions = conditionService.getAllConditions();
        mav.addObject("conditions", conditions);
        List<Medication> medications = medicationService.getAllMedications();
        mav.addObject("medications", medications);
        List<Role> roles = roleService.getAllRoles();
        mav.addObject("roles", roles);
        List<User> agents = userService.getAllUsers();
        mav.addObject("agents", agents);
        return mav;
    }

    @RequestMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/list-users";
    }

    @RequestMapping("/search-users")
    public ModelAndView findUserByKeyword(@RequestParam String keyword) {
        List<User> result = userService.findUserByKeyword(keyword);
        ModelAndView mav = new ModelAndView("admin/search-users");
        mav.addObject("result", result);
        return mav;
    }
    
    @RequestMapping("/view-user/{id}")
    public ModelAndView viewUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/view-user");
        User user = userService.getUser(id);
        mav.addObject("user", user);
        List<Allergy> allergies = allergyService.getAllAllergies();
        mav.addObject("allergies", allergies);
        List<Condition> conditions = conditionService.getAllConditions();
        mav.addObject("conditions", conditions);
        List<Medication> medications = medicationService.getAllMedications();
        mav.addObject("medications", medications);
        List<Role> roles = roleService.getAllRoles();
        mav.addObject("roles", roles);
        User agent = new User();
        if(user.getUserAgentNo() != null) {
            agent = userService.getUser(user.getUserAgentNo());
        }        
        mav.addObject("agent", agent);
        return mav;
    }
}
