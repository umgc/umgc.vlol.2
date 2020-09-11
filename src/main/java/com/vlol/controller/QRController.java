/**
 * Quick Response (QR) code controller class.
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class QRController {
    
    @Autowired
    private QRCodeService qrCodService;
    @GetMapping(value = "/qr-code/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public void qrcodeGenerator(HttpServletResponse response, @PathVariable("barcode") String barcode)
    throws Exception {
        BufferedImage in = qrCodService.generateQRCodeImage(barcode);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(in, "jpeg", os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(is, response.getOutputStream());
    }
    //...
    
    
}
