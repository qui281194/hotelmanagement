/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.models.Feedback;
import fpt.aptech.hotelapi.repository.FeedbackRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class FeedbackService {
     @Autowired
    FeedbackRepository feedbackRepository;
    JavaMailSender javaMailSender;

    /**
     * Constructor của FeedbackService.
     * @param javaMailSender Đối tượng JavaMailSender để gửi email.
     */
    public FeedbackService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Phương thức này gửi email phản hồi cho người dùng.
     * @param feedback Đối tượng Feedback chứa thông tin phản hồi.
     */
    public void sendFeedback(Feedback feedback) {
        String str = "XYZ COP";
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(feedback.getSender(), feedback.getSender());
        mailMessage.setSubject(feedback.getSubject());
        mailMessage.setText(str + "\n" + feedback.getContent());

        mailMessage.setSentDate(new Date());
       

        javaMailSender.send(mailMessage);
    }

    /**
     * Phương thức này lưu trữ phản hồi vào cơ sở dữ liệu.
     * @param newFeedback Đối tượng Feedback mới cần được lưu trữ.
     */
    public void saveFeedback(Feedback newFeedback) {
        Date date = new Date();
        newFeedback.setDated(date.toString());
        newFeedback.markAsPending();
        newFeedback.markAsUnresolved();
        feedbackRepository.save(newFeedback);
    }

    /**
     * Phương thức này trả về danh sách tất cả các phản hồi trong cơ sở dữ liệu.
     * @return Danh sách Feedback.
     */
    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAll();
    }
    /**
     * Phương thức này xóa một phản hồi từ cơ sở dữ liệu.
     * @param feedbackId ID của phản hồi cần xóa.
     */
    public void deleteFeedback(Integer id) {
        feedbackRepository.deleteById(id);
    }
    
    // Tìm kiếm dịch vụ theo từ khóa
    public List<Feedback> searchServices(String sender) {
        return feedbackRepository.searchByNameIgnoreCaseContaining(sender);
    }
}
