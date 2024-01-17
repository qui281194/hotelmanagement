/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.models.Feedback;
import fpt.aptech.hotelapi.service.FeedbackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/feedbackcontroller")
public class FeedbackAPIController {
     private final FeedbackService feedbackService;

    @Autowired
    public FeedbackAPIController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> function_saveFeedback(@RequestBody Feedback newFeedback) {
        feedbackService.saveFeedback(newFeedback);
        feedbackService.sendFeedback(newFeedback);
        return new ResponseEntity<>("Feedback saved successfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> function_getAllFeedbacks() {
        List<Feedback> allFeedbacks = feedbackService.findAllFeedback();
        return new ResponseEntity<>(allFeedbacks, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> function_deleteFeedback(@PathVariable Integer feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return new ResponseEntity<>("Feedback deleted successfully", HttpStatus.OK);
    }
}
