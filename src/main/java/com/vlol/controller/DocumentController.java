/**
 * Medical document controller class.
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

import com.vlol.model.Document;
import com.vlol.model.User;
import com.vlol.service.DocumentService;
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

/** Medical document controller class. */
@Controller
public class DocumentController {
  @Autowired private UserService userService;

  @Autowired private DocumentService documentService;

  @RequestMapping(value = {"/user/documents", "/user/documents/{id}"})
  public ModelAndView viewDocumentList(
      @PathVariable(name = "id", required = false) Long id, Model model, Principal principal) {
    ModelAndView mav = new ModelAndView("user/documents");
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    Utils.getUserData(userService, mav, user);
    model.addAttribute("documentList", user.getDocuments());
    return mav;
  }

  @GetMapping(value = {"/user/get-document/{documentId}", "/user/get-document/{id}/{documentId}"})
  @ResponseBody
  public void getDocument(
      HttpServletResponse response,
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "documentId") Long documentId)
      throws IOException {
    try {
      User user = Utils.getIfAuthorizedForUser(userService, id, false);
      Document ad = documentService.getDocument(documentId);
      if (!ad.getUser().getUserId().equals(user.getUserId())) {
        response.sendError(403);
        return;
      }
      InputStream is = new ByteArrayInputStream(ad.getDocumentFile());
      response.setContentType(ad.getDocumentContentType());
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION,
          "attachment; filename=\"" + ad.getDocumentFilename() + "\"");
      response.setHeader(HttpHeaders.CONTENT_ENCODING, "deflate");
      IOUtils.copy(is, response.getOutputStream());
    } catch (Exception e) {
      response.sendRedirect("/user/document?uploaderror");
      if (id == null) response.sendRedirect("/user/document?uploaderror");
      else response.sendRedirect("/user/document" + id + "?uploaderror");
    }
  }

  @PostMapping("/user/save-document/{id}")
  public String handleDocumentUpload(
      @RequestParam("documentFile") MultipartFile documentFile,
      @RequestParam(name = "documentId", required = false) Long documentId,
      @RequestParam("documentType") String documentType,
      @PathVariable(name = "id", required = false) Long id) {
    User user = Utils.getIfAuthorizedForUser(userService, id, true);

    // Compress the bytes
    try {
      Document ad = new Document();
      if (documentFile.getBytes().length == 0 && documentId != null) {
        Document oldAd = documentService.getDocument(documentId);
        ad.setDocumentContentType(oldAd.getDocumentContentType());
        ad.setDocumentFilename(oldAd.getDocumentFilename());
        ad.setDocumentFile(oldAd.getDocumentFile());
      } else {
        ByteBuffer output = ByteBuffer.allocate(documentFile.getBytes().length);

        Deflater compresser = new Deflater();
        compresser.setInput(documentFile.getBytes());
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();
        byte[] outputDest = new byte[compressedDataLength];
        output.flip().get(outputDest, 0, compressedDataLength);
        ad.setDocumentContentType(documentFile.getContentType());
        ad.setDocumentFilename(
            documentFile
                .getOriginalFilename()
                .replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", ""));
        ad.setDocumentFile(outputDest);
      }
      ad.setDocumentId(documentId);
      ad.setDocumentType(documentType);

      ad.setUser(user);
      documentService.saveDocument(ad);
      return "redirect:/user/documents/" + id;
    } catch (Exception e) {
      e.printStackTrace();
      return "redirect:/user/add-document/" + id + "?error";
    }
  }

  @RequestMapping(value = {"/user/add-document", "/user/add-document/{id}"})
  public ModelAndView viewAddDocumentPage(
      @PathVariable(name = "id", required = false) Long id, Model model) {
    User user = Utils.getIfAuthorizedForUser(userService, id, false);
    if (user == null) return new ModelAndView("redirect:/login");

    ModelAndView mav = new ModelAndView("user/add-edit-document");
    Utils.getUserData(userService, mav, user);
    Document document = new Document();
    document.setUser(user);
    model.addAttribute("document", document);
    return mav;
  }

  @RequestMapping(
      value = {"/user/edit-document/{documentId}", "/user/edit-document/{id}/{documentId}"})
  public ModelAndView viewEditDocumentPage(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "documentId") Long documentId,
      Model model) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return new ModelAndView("redirect:/login");
    ModelAndView mav = new ModelAndView("user/add-edit-document");
    // Check if the document belongs to the user
    Boolean found = false;
    for (Document document : user.getDocuments())
      if (document.getDocumentId().equals(documentId)) found = true;
    if (!found) return new ModelAndView("redirect:/login");
    Document document = documentService.getDocument(documentId);
    model.addAttribute("document", document);
    Utils.getUserData(userService, mav, user);
    return mav;
  }

  @RequestMapping(
      value = {"/user/delete-document/{documentId}", "/user/delete-document/{id}/{documentId}"})
  public String deleteDocument(
      @PathVariable(name = "id", required = false) Long id,
      @PathVariable(name = "documentId") Long documentId) {
    // Check if this user can edit the requested user
    User user = Utils.getIfAuthorizedForUser(userService, id, true);
    if (user == null) return "redirect:/login";
    // Check if the document belongs to the user
    Boolean found = false;
    for (Document document : user.getDocuments()) {
      if (document.getDocumentId().equals(documentId)) found = true;
    }
    if (!found) return "redirect:/login";
    documentService.deleteDocument(documentId);
    if (id == null) return "redirect:/user/documents";
    else return "redirect:/user/documents/" + id;
  }
}
