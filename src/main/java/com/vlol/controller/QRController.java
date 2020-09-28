/**
 * Quick Response (QR) code controller class.
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

import com.vlol.service.QRCodeService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class QRController {

  @Autowired private QRCodeService qrCodService;

  @GetMapping(value = "/qr-code/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
  public void qrcodeGenerator(HttpServletResponse response, @PathVariable("barcode") String barcode)
      throws Exception {
    BufferedImage in = qrCodService.generateQRCodeImage(barcode);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(
        in, "jpeg", os); // Passing: ​(RenderedImage im, String formatName, OutputStream output)
    InputStream is = new ByteArrayInputStream(os.toByteArray());
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    IOUtils.copy(is, response.getOutputStream());
  }
  // ...

}
