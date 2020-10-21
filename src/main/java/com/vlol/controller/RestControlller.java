/**
 * User controller class.
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

import com.vlol.model.AdvanceDirective;
import com.vlol.model.Document;
import com.vlol.model.User;
import com.vlol.service.AdvanceDirectiveService;
import com.vlol.service.DocumentService;
import com.vlol.service.UserService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/** Rest controller class. */
@RestController
public class RestControlller {

  @Autowired private UserService userService;
  @Autowired private AdvanceDirectiveService advanceDirective;
  @Autowired private DocumentService documentService;

  @GetMapping("/api/list-users")
  public List<User> viewUserList() {
    List<User> userList;
    userList = userService.getAllParticipants();
    return userList;
  }

  @GetMapping("/api/user/{id}")
  public User viewProfilePage(@PathVariable(name = "id") Long id) {
    User user = userService.getUser(id);
    return user;
  }

  @GetMapping("/api/advance-directive/{advanceDirectiveId}")
  @ResponseBody
  public void getAdvanceDirective(
      HttpServletResponse response,
      @PathVariable(name = "advanceDirectiveId") Long advanceDirectiveId)
      throws IOException {
    try {
      AdvanceDirective ad = advanceDirective.getAdvanceDirective(advanceDirectiveId);
      InputStream is = new ByteArrayInputStream(ad.getAdvanceDirectiveFile());
      response.setContentType(ad.getAdvanceDirectiveContentType());
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION,
          "attachment; filename=\"" + ad.getAdvanceDirectiveFilename() + "\"");
      response.setHeader(HttpHeaders.CONTENT_ENCODING, "deflate");
      IOUtils.copy(is, response.getOutputStream());
    } catch (Exception e) {
      response.sendError(500);
    }
  }

  @GetMapping("/api/document/{documentId}")
  @ResponseBody
  public void getDocument(
      HttpServletResponse response, @PathVariable(name = "documentId") Long documentId)
      throws IOException {
    try {
      Document ad = documentService.getDocument(documentId);
      InputStream is = new ByteArrayInputStream(ad.getDocumentFile());
      response.setContentType(ad.getDocumentContentType());
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION,
          "attachment; filename=\"" + ad.getDocumentFilename() + "\"");
      response.setHeader(HttpHeaders.CONTENT_ENCODING, "deflate");
      IOUtils.copy(is, response.getOutputStream());
    } catch (Exception e) {
      response.sendError(500);
    }
  }

  @GetMapping("/api/search-users")
  public List<User> findUserByKeyword(@RequestParam String keyword) {
    List<User> result = userService.findUserByKeyword(keyword);
    return result;
  }
}
