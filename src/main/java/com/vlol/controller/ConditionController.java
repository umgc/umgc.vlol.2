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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Medical condition controller class.
 *
 */
@Controller
public class ConditionController {

    @Autowired
    private ConditionService conditionService;
    
    @GetMapping("/search-conditions")
    public @ResponseBody List<Condition> findConditionByKeyword(@RequestParam String keyword) {
        List<Condition> meds =  conditionService.findConditionByKeyword(keyword);
        return meds.subList(0, Math.min(20, meds.size()));
    }
}
