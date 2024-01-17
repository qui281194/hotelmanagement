/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.hotelapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ASUS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_feedback")
public class Feedback {
    // Mã phản hồi, tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Người gửi phản hồi
    @Column(name = "sender")
    private String sender;

    // Chủ đề của phản hồi
    @Column(name = "subject")
    private String subject;

    // Nội dung của phản hồi
    @Column(name = "content")
    private String content;

    // Ngày gửi phản hồi
    @Column(name = "dated")
    private String dated;

    // Trạng thái xử lý của phản hồi
    @Column(name = "process")
    private String process;

    // Trạng thái tổng thể của phản hồi
    @Column(name = "status")
    private String status;

}
