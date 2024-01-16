///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
// */
//package fpt.aptech.api.service;
//
//import fpt.aptech.api.dto.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.rsocket.server.RSocketServer.Transport;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.context.Context;
//
//
///**
// *
// * @author PC
// */
//@Service
//public class EmailService {
//    private JavaMailSender javaMailSender;
//    
//    @Autowired
//    public EmailService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//    
//    public void setConfirmationCode(UserDto newUserDto, String confirmationCode) {
//        // Tạo một Authenticator object để xác thực đăng nhập vào SMTP server
//    Authenticator auth = new Authenticator() {
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(username, password);
//        }
//    };
//
//    // Tạo một Session object với các cấu hình và Authenticator
//    Session session = Session.getInstance(props, auth);
//
//    try {
//        // Tạo một message object
//        Context context = new Context(session);
//        Context.setFrom(new InternetAddress(username));
//        Context.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newUserDto.getEmail()));
//        Context.setSubject("Email Confirmation");
//        Context.setText("Dear " + newUserDto.getName() + ",\n\n"
//                + "Thank you for registering with our application. Please use the following confirmation code to verify your email address:\n\n"
//                + confirmationCode + "\n\n"
//                + "Regards,\n"
//                + "Your Application Team");
//
//        // Gửi email
//        Transport.send(message);
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Xử lý ngoại lệ khi gửi email
//        // Ví dụ: ghi log lỗi, hiển thị thông báo cho người dùng, v.v.
//    }
//    }
//}
