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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vlol.model.User;
import com.vlol.service.QRCodeService;
import com.vlol.service.UserService;

@RestController
@RequestMapping("/user")
public class QRController {

	@Autowired
	private QRCodeService qrCodeService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/qr-code/{id}")
	public ModelAndView qrcodeGenerator(@PathVariable("id") Long id) {
		User user = Utils.getIfUserOrAdmin(userService, id, true);
		if (user == null)
			return new ModelAndView("redirect:/login");
		ModelAndView mav = new ModelAndView("user/qr-code");
		Long randomId = user.getQrCode();
		Utils.getUserName(userService, mav);
		if (randomId == null) {
			randomId = Calendar.getInstance().getTimeInMillis();
			user.setQrCode(randomId);
		}
		try {
			BufferedImage in = qrCodeService.generateQRCodeImage(id, randomId);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(in, "jpeg", os);
			mav.addObject("qrImage", Base64.getEncoder().encodeToString(os.toByteArray()));
			mav.addObject("userId", id);
			userService.updateUser(user);

		} catch (Exception e) {
			mav.addObject("error", true);
		}

		return mav;
	}

	@GetMapping(value = "/qr-code-delete/{id}")
	public ModelAndView deleteCurrentQrCode(@PathVariable("id") Long id) {
		User user = Utils.getIfUserOrAdmin(userService, id, true);
		if (user == null)
			return new ModelAndView("redirect:/login");
		ModelAndView mav = new ModelAndView("user/qr-code");
		Long randomId = user.getQrCode();
		Utils.getUserName(userService, mav);
		user.setQrCode(null);
		randomId = Calendar.getInstance().getTimeInMillis();
		user.setQrCode(randomId);
		try {
			BufferedImage in = qrCodeService.generateQRCodeImage(id, randomId);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(in, "jpeg", os);
			mav.addObject("qrImage", Base64.getEncoder().encodeToString(os.toByteArray()));
			mav.addObject("userId", id);
			userService.updateUser(user);

		} catch (Exception e) {
			mav.addObject("error", true);
		}

		return new ModelAndView( "redirect:/user/qr-code/" + id );
	}
}
