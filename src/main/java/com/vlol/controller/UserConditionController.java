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
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.controller;

import com.vlol.model.Condition;
import com.vlol.model.User;
import com.vlol.model.UserCondition;
import com.vlol.service.ConditionService;
import com.vlol.service.UserConditionService;
import com.vlol.service.UserService;
import java.security.Principal;
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
 */
@Controller
public class UserConditionController {

    @Autowired
    private UserConditionService userConditionService;
    @Autowired
    private UserService userService;

    @RequestMapping( value = {"/user/conditions", "/user/conditions/{id}"})
    public ModelAndView viewConditionList(@PathVariable(name = "id", required=false) Long id, Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("user/conditions");
        Utils.getUserName(userService, mav);
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return new ModelAndView("redirect:/login");
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("conditionList", user.getConditions());
        return mav;
    }

    @RequestMapping(value = {"/user/save-condition", "/user/save-condition/{id}"}, method = RequestMethod.POST)
    public String saveCondition(@PathVariable(name = "id", required=false) Long id, @Valid UserCondition condition, BindingResult bindingResult, Model model) {
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return "redirect:/login";
        condition.setUser(user);
        if (bindingResult.hasErrors()) {
            return "redirect:/user/add-condition";
        }
        userConditionService.saveCondition(condition);
        return "redirect:/user/conditions";
    }
    
    @RequestMapping(value = {"/user/add-condition", "/user/add-condition/{id}"})
    public ModelAndView viewAddConditionPage(@PathVariable(name = "id", required=false) Long id, Model model) {
        User user = Utils.getIfAuthorizedForUser(userService, id, false);
        if(user == null) return new ModelAndView("redirect:/login");
        
        ModelAndView mav = new ModelAndView("user/add-edit-condition");
        Utils.getUserName(userService, mav);
        UserCondition condition = new UserCondition();
        condition.setUser(user);
        model.addAttribute("condition", condition);
        model.addAttribute("userId", user.getUserId());
        return mav;
    }
    @RequestMapping(value = {"/user/edit-condition/{conditionId}", "/user/edit-condition/{id}/{conditionId}"})
    public ModelAndView viewEditConditionPage(@PathVariable(name = "id", required=false) Long id, @PathVariable(name = "conditionId") Long conditionId, Model model) {
        // Check if this user can edit the requested user
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return new ModelAndView("redirect:/login");
        ModelAndView mav = new ModelAndView("user/add-edit-condition");
        Utils.getUserName(userService, mav);
        // Check if the condition belongs to the user
        Boolean found = false;
        for(UserCondition condition : user.getConditions())
            if(condition.getConditionId().equals(conditionId))
                found = true;
        if(!found)
            return new ModelAndView("redirect:/login");
        UserCondition condition = userConditionService.getCondition(conditionId);
        model.addAttribute("condition", condition);
        model.addAttribute("userId", user.getUserId());
        return mav;
    }

    @RequestMapping(value = {"/user/delete-condition/{conditionId}", "/user/delete-condition/{id}/{conditionId}"})
    public String deleteCondition(@PathVariable(name = "id", required=false) Long id, @PathVariable(name = "conditionId") Long conditionId) {
        // Check if this user can edit the requested user
        User user = Utils.getIfAuthorizedForUser(userService, id, true);
        if(user == null)
            return "redirect:/login";
        // Check if the condition belongs to the user
        Boolean found = false;
        for(UserCondition condition : user.getConditions()){
            if(condition.getConditionId().equals(conditionId))
                found = true;
        }
        if(!found)
            return "redirect:/login";
        userConditionService.deleteCondition(conditionId);
        if(id==null)
            return "redirect:/user/conditions";
        else
            return "redirect:/user/conditions/"+id;
    }
}
