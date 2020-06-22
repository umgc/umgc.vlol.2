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
 * @package configuration
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.controller;

import com.vlol.model.Role;
import com.vlol.service.RoleService;
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
 * Role controller class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list-roles")
    public String viewRoleList(Model model) {
        List<Role> roleList = roleService.listAllRoles();
        model.addAttribute("roleList", roleList);
        return "/admin/list-roles";
    }

    @RequestMapping("/add-role")
    public String viewAddRolePage(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "/admin/add-role";
    }

    @RequestMapping(value = "/save-role", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("role") Role role) {
        roleService.saveRole(role);
        return "redirect:/list-roles";
    }

    @RequestMapping("/edit-role/{id}")
    public ModelAndView viewEditRolePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/edit-role");
        Role role = roleService.getRole(id);
        mav.addObject("role", role);
        return mav;
    }

    @RequestMapping("/delete-role/{id}")
    public String deleteRole(@PathVariable(name = "id") int id) {
        roleService.deleteRole(id);
        return "redirect:/list-roles";
    }

    @RequestMapping("/search-roles")
    public ModelAndView search(@RequestParam String keyword) {
        List<Role> result = roleService.searchForRole(keyword);
        ModelAndView mav = new ModelAndView("admin/search-roles");
        mav.addObject("result", result);
        return mav;
    }
}
