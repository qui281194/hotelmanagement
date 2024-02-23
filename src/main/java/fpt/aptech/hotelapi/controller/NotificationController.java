/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@RestController
public class NotificationController {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailDto emailDto) throws MessagingException, IOException{
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(emailDto.getTo());
//        simpleMailMessage.setSubject(emailDto.getSubject());
//        simpleMailMessage.setText(emailDto.getText());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(emailDto.getTo());
        mimeMessageHelper.setSubject(emailDto.getSubject());
        mimeMessageHelper.setText(emailDto.getText(), true);
        mimeMessageHelper.addAttachment(
                emailDto.getAttachment().getOriginalFilename(),
                converMutipartToFile(
                    emailDto.getAttachment(), emailDto.getAttachment().getOriginalFilename()));
        javaMailSender.send(mimeMessage); 
        
        return "Email sent succesfully";
    }
    
    private static File converMutipartToFile(MultipartFile multipartFile, String fileName)throws IOException{
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipartFile.transferTo(convFile);
        return convFile;
    }
//    public static void main(String[] args){
//        System.out.println(System.getProperty("java.io.tmpdir"));
//    }
    
}
