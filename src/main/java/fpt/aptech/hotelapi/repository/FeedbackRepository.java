/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.Feedback;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ASUS
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT s FROM Feedback s WHERE LOWER(s.sender) LIKE LOWER(CONCAT('%', :sender, '%'))")
    List<Feedback> searchByNameIgnoreCaseContaining(@Param("sender") String sender);
}
