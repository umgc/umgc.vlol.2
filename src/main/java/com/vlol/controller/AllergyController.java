/**
 * Allergy controller class.
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

import com.vlol.model.Allergy;
import com.vlol.service.AllergyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** Allergy controller class. */
@Controller
public class AllergyController {

  @Autowired private AllergyService allergyService;

  @GetMapping("/search-allergies")
  public @ResponseBody List<Allergy> findAllergyByKeyword(@RequestParam String keyword) {
    List<Allergy> meds = allergyService.findAllergyByKeyword(keyword);
    return meds.subList(0, Math.min(20, meds.size()));
  }
}
