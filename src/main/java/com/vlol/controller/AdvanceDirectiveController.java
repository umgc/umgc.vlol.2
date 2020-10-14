/**
 * Medical advanceDirective controller class.
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

import com.vlol.model.AdvanceDirective;
import com.vlol.model.User;
import com.vlol.service.AdvanceDirectiveService;
import com.vlol.service.UserService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.zip.Deflater;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/** Medical advanceDirective controller class. */
@Controller
public class AdvanceDirectiveController {
  @Autowired private UserService userService;

  @Autowired private AdvanceDirectiveService advanceDirectiveService;

  @RequestMapping(value = {"/user/advance-directives", "/user/advance-directives/{id}"})
  public ModelAndView viewAdvanceDirectiveList(
      @PathVariable(name = "id", required = false) Long id, Model model, Principal principal) {
    ModelAndView mav = new ModelAndView("user/advance-directives");
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    Utils.getUserData(userService, mav, user.getUserId());
    model.addAttribute("advanceDirectiveList", user.getAdvanceDirectives());
    return mav;
  }

  @GetMapping(
      value = {
        "/user/get-advance-directive/{advanceDirectiveId}",
        "/user/get-advance-directive/{id}/{advanceDirectiveId}"
      })
  @ResponseBody
  public void getAdvanceDirective(
      HttpServletResponse response,
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "advanceDirectiveId") Long advanceDirectiveId)
      throws IOException {
    try {
      User user = Utils.getIfAuthorizedForUser(userService, id, false);
      AdvanceDirective ad = advanceDirectiveService.getAdvanceDirective(advanceDirectiveId);
      if (!ad.getUser().getUserId().equals(user.getUserId())) {
        response.sendError(403);
        return;
      }
      InputStream is = new ByteArrayInputStream(ad.getAdvanceDirectiveFile());
      response.setContentType(ad.getAdvanceDirectiveContentType());
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION,
          "attachment; filename=\"" + ad.getAdvanceDirectiveFilename() + "\"");
      response.setHeader(HttpHeaders.CONTENT_ENCODING, "deflate");
      IOUtils.copy(is, response.getOutputStream());
    } catch (Exception e) {
      response.sendRedirect("/user/advance-directive?uploaderror");
    }
  }

  @PostMapping("/user/save-advance-directive/{id}")
  public String handleAdvanceDirectiveUpload(
      @RequestParam("advanceDirectiveFile") MultipartFile advanceDirectiveFile,
      @RequestParam(name = "advanceDirectiveId", required = false) Long advanceDirectiveId,
      @RequestParam("advanceDirectiveType") String advanceDirectiveType,
      @PathVariable(name = "id", required = false) Long id) {
    User user = Utils.getIfAuthorizedForUser(userService, id, true);

    // Compress the bytes
    try {
      AdvanceDirective ad = new AdvanceDirective();
      if (advanceDirectiveFile.getBytes().length == 0 && advanceDirectiveId != null) {
        AdvanceDirective oldAd = advanceDirectiveService.getAdvanceDirective(advanceDirectiveId);
        ad.setAdvanceDirectiveContentType(oldAd.getAdvanceDirectiveContentType());
        ad.setAdvanceDirectiveFilename(oldAd.getAdvanceDirectiveFilename());
        ad.setAdvanceDirectiveFile(oldAd.getAdvanceDirectiveFile());
      } else {
        ByteBuffer output = ByteBuffer.allocate(advanceDirectiveFile.getBytes().length);

        Deflater compresser = new Deflater();
        compresser.setInput(advanceDirectiveFile.getBytes());
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();
        byte[] outputDest = new byte[compressedDataLength];
        output.flip().get(outputDest, 0, compressedDataLength);
        ad.setAdvanceDirectiveContentType(advanceDirectiveFile.getContentType());
        ad.setAdvanceDirectiveFilename(
            advanceDirectiveFile
                .getOriginalFilename()
                .replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", ""));
        ad.setAdvanceDirectiveFile(outputDest);
      }
      ad.setAdvanceDirectiveId(advanceDirectiveId);
      ad.setAdvanceDirectiveType(advanceDirectiveType);

      ad.setUser(user);
      advanceDirectiveService.saveAdvanceDirective(ad);
      return "redirect:/user/advance-directives/" + id;
    } catch (Exception e) {
      e.printStackTrace();
      return "redirect:/user/add-advance-directive/" + id + "?error";
    }
  }

  @RequestMapping(value = {"/user/add-advance-directive", "/user/add-advance-directive/{id}"})
  public ModelAndView viewAddAdvanceDirectivePage(
      @PathVariable(name = "id", required = false) Long id, Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, false);
    if (user == null) return new ModelAndView("redirect:/login");

    ModelAndView mav = new ModelAndView("user/add-edit-advance-directive");
    Utils.getUserData(userService, mav, user.getUserId());
    AdvanceDirective advanceDirective = new AdvanceDirective();
    advanceDirective.setUser(user);
    model.addAttribute("advanceDirective", advanceDirective);
    return mav;
  }

  @RequestMapping(
      value = {
        "/user/edit-advance-directive/{advanceDirectiveId}",
        "/user/edit-advance-directive/{id}/{advanceDirectiveId}"
      })
  public ModelAndView viewEditAdvanceDirectivePage(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "advanceDirectiveId") Long advanceDirectiveId,
      Model model) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    ModelAndView mav = new ModelAndView("user/add-edit-advance-directive");
    // Check if the advanceDirective belongs to the user
    Boolean found = false;
    for (AdvanceDirective advanceDirective : user.getAdvanceDirectives())
      if (advanceDirective.getAdvanceDirectiveId().equals(advanceDirectiveId)) found = true;
    if (!found) return new ModelAndView("redirect:/login");
    AdvanceDirective advanceDirective =
        advanceDirectiveService.getAdvanceDirective(advanceDirectiveId);
    model.addAttribute("advanceDirective", advanceDirective);
    Utils.getUserData(userService, mav, user.getUserId());
    return mav;
  }

  @RequestMapping(
      value = {
        "/user/delete-advance-directive/{advanceDirectiveId}",
        "/user/delete-advance-directive/{id}/{advanceDirectiveId}"
      })
  public String deleteAdvanceDirective(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "advanceDirectiveId") Long advanceDirectiveId) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    // Check if the advanceDirective belongs to the user
    Boolean found = false;
    for (AdvanceDirective advanceDirective : user.getAdvanceDirectives()) {
      if (advanceDirective.getAdvanceDirectiveId().equals(advanceDirectiveId)) found = true;
    }
    if (!found) return "redirect:/login";
    advanceDirectiveService.deleteAdvanceDirective(advanceDirectiveId);
    if (id == null) return "redirect:/user/advance-directives";
    else return "redirect:/user/advance-directives/" + id;
  }
}
