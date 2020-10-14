/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.vlol.controller.Utils;
import java.awt.image.BufferedImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/** @author panhavornhok */
@Service
public class QRCodeService {

  @Autowired UserService userService;

  @Autowired private Environment env;

  public BufferedImage generateQRCodeImage(Long userId, String randomID) throws Exception {
    String dns = env.getProperty("mail.smtp.urlPath");
    QRCodeWriter barcodeWriter = new QRCodeWriter();
    String jwtToken = Utils.createJWT(userService.getUser(userId));
    jwtToken += "?code=" + randomID;
    BitMatrix bitMatrix =
        barcodeWriter.encode(
            dns + "user/view/" + userId + "?jwt=" + jwtToken, BarcodeFormat.QR_CODE, 400, 400);
    return MatrixToImageWriter.toBufferedImage(bitMatrix);
  }
}
