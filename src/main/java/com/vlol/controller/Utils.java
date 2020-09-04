/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vlol.model.User;
import com.vlol.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author marcuccm
 */
public class Utils {
    private static final String secretKey = "al3epUn9wy0TLAIDMODFVWA;R]p&>v=ZgqJX7i_%d>e=sS?0[Ehfm]o8G):V\"P~";
    private static final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    
    public static void getUserName(UserService userService, ModelAndView mav) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            User user = userService.findUserByEmail(auth.getName());
            mav.addObject("userRealName", user.getFirstName() + " " + user.getLastName());
        }
    }
    public static User getIfAuthorizedForUser(UserService userService){
        return getIfAuthorizedForUser(userService, null, false);
    }
    public static User getIfAuthorizedForUser(UserService userService, Boolean editable){
        return getIfAuthorizedForUser(userService, null, editable);
    }
    public static User getIfAuthorizedForUser(UserService userService, Long userId, Boolean editable){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            // Check if user is admin or provider
            if(isAdmin() || (isProvider() && !editable)){
                return userId!=null?userService.getUser(userId):userService.findUserByEmail(auth.getName());
            }else{
                ArrayList emails = new ArrayList<String>();
                User user = userId!=null?userService.getUser(userId):userService.findUserByEmail(auth.getName());
                user.getAuthorizedEmails().forEach(ae->emails.add(ae.getAuthorizedEmail().toLowerCase()));
                // Check if user is authorized for this user or if the user is itself
                if(emails.contains(auth.getName()) || user.getEmail().equals(auth.getName())) return user;
            }
        }
        return null;
    }
    public static Boolean isAdmin(){
        ArrayList authorities = new ArrayList<String>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach((a)->{
            authorities.add(a.getAuthority());
        });
        return authorities.contains("admin");
    }
    public static Boolean isProvider(){
        ArrayList authorities = new ArrayList<String>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach((a)->{
            authorities.add(a.getAuthority());
        });
        return authorities.contains("provider");
    }
    public static Boolean isParticipant(){
        ArrayList authorities = new ArrayList<String>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach((a)->{
            authorities.add(a.getAuthority());
        });
        return authorities.contains("participant");
    }
    public static String createJWT(User user){
        return JWT.create()
                .withClaim("userId", user.getUserID())
                .sign(algorithm);
    }
    public static Boolean verifyJWT(User user, String jwt){
        JWTVerifier verifier = JWT.require(algorithm)
            .withClaim("userId", user.getUserID())
            .build();
        try {
            verifier.verify(jwt);
            return true;
        } catch(JWTVerificationException e) {
            
        }
        return false;
    }
}
