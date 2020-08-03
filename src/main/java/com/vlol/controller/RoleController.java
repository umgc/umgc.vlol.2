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
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list-roles", method = RequestMethod.GET)
    public ModelAndView viewRoleList() {
        ModelAndView mav = new ModelAndView("admin/list-roles");
        mav = getUserName(mav);
        List<Role> roleList = roleService.getAllRoles();
        mav.addObject("roleList", roleList);
        return mav;
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.GET)
    public ModelAndView viewAddRolePage() {
        ModelAndView mav = new ModelAndView("admin/add-role");
        mav = getUserName(mav);
        Role role = new Role();
        mav.addObject("role", role);
        return mav;
    }

    @RequestMapping(value = "/save-role", method = RequestMethod.POST)
    public String saveRole(@Valid Role role, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/add-role";
        }
        roleService.saveRole(role);
        return "redirect:/list-roles";
    }

    @RequestMapping(value = "/update-role", method = RequestMethod.POST)
    public String updateRole(@Valid Role role, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/edit-role";
        }
        roleService.saveRole(role);
        return "redirect:/list-roles";
    }

    @RequestMapping("/edit-role/{id}")
    public ModelAndView viewEditRolePage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/edit-role");
        mav = getUserName(mav);
        Role role = roleService.getRole(id);
        mav.addObject("role", role);
        return mav;
    }

    @RequestMapping("/delete-role/{id}")
    public String deleteRole(@PathVariable(name = "id") Long id) {
        roleService.deleteRole(id);
        return "redirect:/list-roles";
    }

    @RequestMapping("/search-roles")
    public ModelAndView findRoleByKeyword(@RequestParam String keyword) {
        ModelAndView mav = new ModelAndView("admin/search-roles");
        mav = getUserName(mav);
        List<Role> result = roleService.findRoleByKeyword(keyword);
        mav.addObject("result", result);
        return mav;
    }

    @RequestMapping("/view-role/{id}")
    public ModelAndView viewRolePage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/view-role");
        mav = getUserName(mav);
        Role role = roleService.getRole(id);
        mav.addObject("role", role);
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
