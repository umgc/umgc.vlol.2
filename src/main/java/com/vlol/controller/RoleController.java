/**
 * Role controller class.
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

import com.vlol.model.Role;
import com.vlol.service.RoleService;
import com.vlol.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** Role controller class. */
@Controller
public class RoleController {

  @Autowired private RoleService roleService;

  @Autowired private UserService userService;

  @RequestMapping(value = "/list-roles", method = RequestMethod.GET)
  public ModelAndView viewRoleList() {
    ModelAndView mav = new ModelAndView("admin/list-roles");
    Utils.getUserData(userService, mav);
    List<Role> roleList = roleService.getAllRoles();
    mav.addObject("roleList", roleList);
    return mav;
  }

  @RequestMapping("/search-roles")
  public ModelAndView findRoleByKeyword(@RequestParam String keyword) {
    ModelAndView mav = new ModelAndView("admin/search-roles");
    Utils.getUserData(userService, mav);
    List<Role> result = roleService.findRoleByKeyword(keyword);
    mav.addObject("result", result);
    return mav;
  }

  @RequestMapping("/view-role/{id}")
  public ModelAndView viewRolePage(@PathVariable(name = "id") Long id) {
    ModelAndView mav = new ModelAndView("admin/view-role");
    Utils.getUserData(userService, mav);
    Role role = roleService.getRole(id);
    mav.addObject("role", role);
    return mav;
  }
}
