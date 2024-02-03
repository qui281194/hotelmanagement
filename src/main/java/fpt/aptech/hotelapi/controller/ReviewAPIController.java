/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.ReviewDto;
import fpt.aptech.hotelapi.service.ReviewService;
import java.util.List;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/reviewcontroller")
public class ReviewAPIController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewAPIController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/all")
    public List<ReviewDto> getAllReviews() {
        return reviewService.allReview();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReviewDto>> searchReviewsByText(@RequestParam String reviewText) {
        List<ReviewDto> reviews = (List<ReviewDto>) reviewService.findReviewByText(reviewText);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ReviewDto createNewReview(@RequestBody ReviewDto newReviewDto) {
        return reviewService.createNewReview(newReviewDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable int id) throws Exception {
        reviewService.deleteReview(id);
    }

    @PutMapping("/edit/{id}")
    public ReviewDto editReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        return reviewService.editReview(id, reviewDto);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<ReviewDto> disableReview(@PathVariable int id) {
        try {
            ReviewDto disabledReview = reviewService.disableReview(id);
            return new ResponseEntity<>(disabledReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

