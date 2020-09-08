/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol;

import com.vlol.controller.Utils;
import com.vlol.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 *
 * @author marcuccm
 */
public class Mailer  {
    static Mailer instance;
    Properties emailProps = new Properties();
    
    Session session;
    public static Mailer getInstance(){
        return instance;
    }
    public Mailer(Environment env){
        if(instance == null){
            emailProps.setProperty("appName", env.getProperty("spring.application.name"));
            emailProps.setProperty("noReplyEmail", env.getProperty("mail.smtp.noReplyEmail"));
            emailProps.setProperty("supportEmail", env.getProperty("mail.smtp.supportEmail"));
            emailProps.setProperty("urlPath", env.getProperty("mail.smtp.urlPath"));
            
            try{
                if(env.getProperty("mail.smtp.logoLocation") != null && !env.getProperty("mail.smtp.logoLocation").isBlank())
                emailProps.setProperty("logo", "data:image/png;base64,"+Base64.getEncoder().encodeToString(Mailer.class.getResourceAsStream(env.getProperty("mail.smtp.logoLocation")).readAllBytes()));
            }catch(IOException e){
                e.printStackTrace();
            }
            if(env.getProperty("mail.smtp.logo") != null && !env.getProperty("mail.smtp.logo").isBlank())
                emailProps.setProperty("logo", env.getProperty("mail.smtp.logo"));
            String host = env.getProperty("mail.smtp.host");
            String port = env.getProperty("mail.smtp.port");
            String username = env.getProperty("mail.smtp.username");
            String password = env.getProperty("mail.smtp.password");
            Properties prop = new Properties();
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", port);
            prop.put("mail.smtp.auth", "true");
            if(port.equals("465")){
                prop.put("mail.smtp.socketFactory.port", "465");    
                prop.put("mail.smtp.socketFactory.class",    
                          "javax.net.ssl.SSLSocketFactory");
            }
            if(username.isBlank()){
                session = Session.getInstance(prop);
            }else{
                session = Session.getInstance(prop, new javax.mail.Authenticator() {    
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);  
                    }
                });
            }
            instance = this;
        }
    }
    private void sendMail(String to, String subject, String templateName, Properties props){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailProps.getProperty("noReplyEmail")));
            message.setRecipients(
              Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(fillTemplate(templateName, props), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email message sent to "+to);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private String fillTemplate(String templateName, Properties props) throws IOException{
        String message = new String(Mailer.class.getResourceAsStream("/email/"+templateName).readAllBytes());
        Matcher m = Pattern.compile("\\{\\{([^{}]+)\\}\\}").matcher(message);
        HashSet<String> matches = new HashSet<>();
        while (m.find()) {
            matches.add(m.group(1));
        }
        props.setProperty("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        for(String variable : matches){
            if(props.getProperty(variable) != null){
                message = message.replaceAll("\\{\\{"+variable+"\\}\\}", props.getProperty(variable));
            }
            else if(emailProps.getProperty(variable) != null){
                message = message.replaceAll("\\{\\{"+variable+"\\}\\}", emailProps.getProperty(variable));
            }else{
                System.err.println("Missing variable '"+variable+"' for email, value is not set");
            }
        }
        return message;
    }
    public void verifyEmail(User user){
        Properties props = new Properties();
        props.setProperty("link", emailProps.getProperty("urlPath")+"verify-email?jwt="+Utils.createJWT(user, 1000L*60*60*24));
        instance.sendMail(user.getEmail(), "Verify Email | "+emailProps.getProperty("appName"), "verify-email.html", props);
    }
    public void resetPassword(User user){
        Properties props = new Properties();
        props.setProperty("link", emailProps.getProperty("urlPath")+"reset-password?jwt="+Utils.createJWT(user, 1000L*60*60*24));
        instance.sendMail(user.getEmail(), "Reset Password | "+emailProps.getProperty("appName"), "reset-password.html", props);
    }
}
