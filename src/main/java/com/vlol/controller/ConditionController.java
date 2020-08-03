/**
 * Medical condition controller class.
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

import com.vlol.model.Condition;
import com.vlol.model.User;
import com.vlol.service.ConditionService;
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
 * Medical condition controller class.
 *
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 */
@Controller
public class ConditionController {

    @Autowired
    private ConditionService conditionService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list-conditions", method = RequestMethod.GET)
    public ModelAndView viewConditionList() {
        ModelAndView mav = new ModelAndView("admin/list-conditions");
        mav = getUserName(mav);
        List<Condition> conditionList = conditionService.getAllConditions();
        mav.addObject("conditionList", conditionList);
        return mav;
    }

    @RequestMapping(value = "/add-condition", method = RequestMethod.GET)
    public ModelAndView viewAddConditionPage() {
        ModelAndView mav = new ModelAndView("admin/add-condition");
        mav = getUserName(mav);
        Condition condition = new Condition();
        mav.addObject("condition", condition);
        return mav;
    }

    @RequestMapping(value = "/save-condition", method = RequestMethod.POST)
    public String saveCondition(@Valid Condition condition, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/add-condition";
        }
        conditionService.saveCondition(condition);
        return "redirect:/list-conditions";
    }

    @RequestMapping(value = "/update-condition", method = RequestMethod.POST)
    public String updateCondition(@Valid Condition condition, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "admin/edit-condition";
        }
        conditionService.saveCondition(condition);
        return "redirect:/list-conditions";
    }

    @RequestMapping("/edit-condition/{id}")
    public ModelAndView viewEditConditionPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/edit-condition");
        mav = getUserName(mav);
        Condition condition = conditionService.getCondition(id);
        mav.addObject("condition", condition);
        return mav;
    }

    @RequestMapping("/delete-condition/{id}")
    public String deleteCondition(@PathVariable(name = "id") Long id) {
        conditionService.deleteCondition(id);
        return "redirect:/list-conditions";
    }

    @RequestMapping("/search-conditions")
    public ModelAndView findConditionByKeyword(@RequestParam String keyword) {
        ModelAndView mav = new ModelAndView("admin/search-conditions");
        mav = getUserName(mav);
        List<Condition> result = conditionService.findConditionByKeyword(keyword);
        mav.addObject("result", result);
        return mav;
    }

    @RequestMapping("/view-condition/{id}")
    public ModelAndView viewConditionPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/view-condition");
        mav = getUserName(mav);
        Condition condition = conditionService.getCondition(id);
        mav.addObject("condition", condition);
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
