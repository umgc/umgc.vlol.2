/**
 * API Keys controller class.
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
 */
package com.vlol.controller;

import com.vlol.model.APIKeys;
import com.vlol.service.APIKeysService;
import com.vlol.service.UserService;
import java.security.Principal;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** API Keys controller class. */
@Controller
public class APIKeysController {
  @Autowired private UserService userService;

  @Autowired private APIKeysService apiKeysService;

  @RequestMapping("/admin/api-keys")
  public ModelAndView viewAPIKeysList(Model model, Principal principal) {
    ModelAndView mav = new ModelAndView("admin/api-keys");
    Utils.getUserName(userService, mav);
    if (!Utils.isAdmin()) return new ModelAndView("redirect:/login");
    model.addAttribute("apiKeysList", apiKeysService.getAllAPIKeys());
    return mav;
  }

  @RequestMapping(value = "/admin/save-api-key", method = RequestMethod.POST)
  public String saveApiKey(@Valid APIKeys apiKey, BindingResult bindingResult, Model model) {
    if (!Utils.isAdmin()) return "redirect:/login";
    if (bindingResult.hasErrors()) {
      return "redirect:/admin/add-api-key";
    }
    apiKeysService.saveApiKey(apiKey);
    return "redirect:/admin/api-keys";
  }

  @RequestMapping("/admin/add-api-key")
  public ModelAndView viewAddAPIKeysPage(Model model) {
    if (!Utils.isAdmin()) return new ModelAndView("redirect:/login");

    ModelAndView mav = new ModelAndView("admin/add-edit-api-key");
    Utils.getUserName(userService, mav);
    APIKeys apiKey = new APIKeys();
    apiKey.setApiKey(UUID.randomUUID().toString());
    model.addAttribute("apiKey", apiKey);
    return mav;
  }

  @RequestMapping("/admin/edit-api-key/{apiKeyId}")
  public ModelAndView viewEditAPIKeysPage(
      @PathVariable(name = "apiKeyId") String apiKeyId, Model model) {
    // Check if this user can edit
    if (!Utils.isAdmin()) return new ModelAndView("redirect:/login");
    ModelAndView mav = new ModelAndView("admin/add-edit-api-key");
    Utils.getUserName(userService, mav);
    APIKeys apiKey = apiKeysService.getAPIKey(apiKeyId);
    model.addAttribute("apiKey", apiKey);
    return mav;
  }

  @RequestMapping("/admin/delete-api-key/{apiKeyId}")
  public String deleteAPIKeys(@PathVariable(name = "apiKeyId") String apiKeyId) {
    // Check if this user can edit
    if (!Utils.isAdmin()) return "redirect:/login";
    apiKeysService.deleteAPIKey(apiKeyId);
    return "redirect:/admin/api-keys";
  }
}
