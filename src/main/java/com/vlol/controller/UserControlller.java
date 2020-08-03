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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    private Map<String, Allergy> allergyCache;
    private Map<String, Condition> conditionCache;
    private Map<String, Medication> medicationCache;

    @RequestMapping("/list-users")
    public ModelAndView viewUserList() {
        ModelAndView mav = new ModelAndView("admin/list-users");
        mav = getUserName(mav);
        List<User> userList = userService.getAllUsers();
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping("/add-user")
    public ModelAndView viewAddUserPage() {
        User user = new User();
        ModelAndView mav = new ModelAndView("admin/add-user");
        mav = getUserName(mav);
        mav.addObject("user", user);
        List<Allergy> allergies = allergyService.getAllAllergies();
        allergyCache = new HashMap<String, Allergy>();
        for (Allergy allergy : allergies) {
            allergyCache.put(allergy.getIdAsString(), allergy);
        }
        mav.addObject("allergies", allergies);
        List<Condition> conditions = conditionService.getAllConditions();
        conditionCache = new HashMap<String, Condition>();
        for (Condition condition : conditions) {
            conditionCache.put(condition.getIdAsString(), condition);
        }
        mav.addObject("conditions", conditions);
        List<Medication> medications = medicationService.getAllMedications();
        medicationCache = new HashMap<String, Medication>();
        for (Medication medication : medications) {
            medicationCache.put(medication.getIdAsString(), medication);
        }
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
    public ModelAndView viewEditUserPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/edit-user");
        mav = getUserName(mav);
        User user = userService.getUser(id);
        mav.addObject("user", user);
        List<Allergy> allergies = allergyService.getAllAllergies();
        allergyCache = new HashMap<String, Allergy>();
        for (Allergy allergy : allergies) {
            allergyCache.put(allergy.getIdAsString(), allergy);
        }
        mav.addObject("allergies", allergies);
        List<Condition> conditions = conditionService.getAllConditions();
        conditionCache = new HashMap<String, Condition>();
        for (Condition condition : conditions) {
            conditionCache.put(condition.getIdAsString(), condition);
        }
        mav.addObject("conditions", conditions);
        List<Medication> medications = medicationService.getAllMedications();
        medicationCache = new HashMap<String, Medication>();
        for (Medication medication : medications) {
            medicationCache.put(medication.getIdAsString(), medication);
        }
        mav.addObject("medications", medications);
        List<Role> roles = roleService.getAllRoles();
        mav.addObject("roles", roles);
        List<User> agents = userService.getAllUsers();
        mav.addObject("agents", agents);
        return mav;
    }

    @RequestMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/list-users";
    }

    @RequestMapping("/search-users")
    public ModelAndView findUserByKeyword(@RequestParam String keyword) {
        List<User> result = userService.findUserByKeyword(keyword);
        ModelAndView mav = new ModelAndView("admin/search-users");
        mav = getUserName(mav);
        mav.addObject("result", result);
        return mav;
    }

    @RequestMapping("/view-user/{id}")
    public ModelAndView viewUserPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/view-user");
        mav = getUserName(mav);
        User user = userService.getUser(id);
        mav.addObject("user", user);
        List<Allergy> allergies = allergyService.getAllAllergies();
        allergyCache = new HashMap<String, Allergy>();
        for (Allergy allergy : allergies) {
            allergyCache.put(allergy.getIdAsString(), allergy);
        }
        mav.addObject("allergies", allergies);
        List<Condition> conditions = conditionService.getAllConditions();
        conditionCache = new HashMap<String, Condition>();
        for (Condition condition : conditions) {
            conditionCache.put(condition.getIdAsString(), condition);
        }
        mav.addObject("conditions", conditions);
        List<Medication> medications = medicationService.getAllMedications();
        medicationCache = new HashMap<String, Medication>();
        for (Medication medication : medications) {
            medicationCache.put(medication.getIdAsString(), medication);
        }
        mav.addObject("medications", medications);
        List<Role> roles = roleService.getAllRoles();
        mav.addObject("roles", roles);
        User agent = new User();
        if (user.getUserAgentNo() != null) {
            agent = userService.getUser(user.getUserAgentNo());
        }
        mav.addObject("agent", agent);
        return mav;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Set.class, "allergies", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof Allergy) {
                    System.out.println("Converting from Allergy to Allergy: " + element);
                    return element;
                }
                if (element instanceof String) {
                    Allergy allergy = allergyCache.get(element);
                    System.out.println("Looking up allergy for id " + element + ": " + allergy);
                    return allergy;
                }
                System.out.println("Don't know what to do with: " + element);
                return null;
            }
        });
        binder.registerCustomEditor(Set.class, "conditions", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof Condition) {
                    System.out.println("Converting from Condition to Condition: " + element);
                    return element;
                }
                if (element instanceof String) {
                    Condition condition = conditionCache.get(element);
                    System.out.println("Looking up condition for id " + element + ": " + condition);
                    return condition;
                }
                System.out.println("Don't know what to do with: " + element);
                return null;
            }
        });
        binder.registerCustomEditor(Set.class, "medications", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof Medication) {
                    System.out.println("Converting from Medication to Medication: " + element);
                    return element;
                }
                if (element instanceof String) {
                    Medication medication = medicationCache.get(element);
                    System.out.println("Looking up medication for id " + element + ": " + medication);
                    return medication;
                }
                System.out.println("Don't know what to do with: " + element);
                return null;
            }
        });
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
