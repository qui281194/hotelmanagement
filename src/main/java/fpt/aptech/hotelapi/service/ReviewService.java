/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.ReviewDto;
import fpt.aptech.hotelapi.dto.RoomDto;
import fpt.aptech.hotelapi.dto.UserDto;
import fpt.aptech.hotelapi.models.Review;
import fpt.aptech.hotelapi.models.Room;
import fpt.aptech.hotelapi.models.Users;
import fpt.aptech.hotelapi.repository.ReviewRepository;
import fpt.aptech.hotelapi.repository.RoomRepository;
import fpt.aptech.hotelapi.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class ReviewService {

    private final RoomRepository roomRepo;
    private final UserRepository userRepo;
    private final ReviewRepository reviewRepo;

    @Autowired
    public ReviewService(RoomRepository roomRepo, UserRepository userRepo, ReviewRepository reviewRepo) {
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
    }

    private Review mapToModel(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setReviewtext(reviewDto.getReviewtext());
        review.setDatesubmited(reviewDto.getDatesubmited());
        review.setRating(reviewDto.getRating());
        review.setStatus(reviewDto.isStatus());

        // User mapping
        Users user = new Users();
        user.setId(reviewDto.getUser_id());
        review.setUser(user); // pass

        // Room mapping
        Room room = new Room();
        room.setId(reviewDto.getRoom_id());
        review.setRoom(room);

        return review;
    }

    public ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setReviewtext(review.getReviewtext());
        reviewDto.setDatesubmited(review.getDatesubmited());
        reviewDto.setRating(review.getRating());
        reviewDto.setStatus(review.isStatus());

        // User mapping
        if (review.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(review.getUser().getId());
            reviewDto.setUser_id(review.getUser().getId());
            reviewDto.setUser(userDto);
            // Set the username using the getUsernameByUserId method
            reviewDto.setUsername(getUsernameByUserId(review.getUser().getId()));
        }

        // Room mapping
        if (review.getRoom() != null) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(review.getRoom().getId());
            reviewDto.setRoom_id(review.getRoom().getId());
            reviewDto.setRoom(roomDto);
            // Set the room_no using the getRoomNoByRoomId method
            reviewDto.setRoom_no(getRoomNoByRoomId(review.getRoom().getId()));
        }

        return reviewDto;
    }

    public List<ReviewDto> allReview() {
        return reviewRepo.findAll().stream()
                .map(mapper -> mapToDto(mapper))
                .collect(Collectors.toList());
    }

    public ReviewDto findReviewByText(String reviewtext) {
        List<Review> review = reviewRepo.findByReviewtextContainingIgnoreCase(reviewtext);
        return (ReviewDto) review.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteReview(int id) throws Exception {
        Optional<Review> reviewOptinal = reviewRepo.findById(id);
        if (reviewOptinal.isPresent()) {
            reviewRepo.deleteById(id);
        } else {
            throw new Exception("Review Not Found");
        }
    }

    public ReviewDto disableReview(int id) {
        Optional<Review> existingReviewOptinal = reviewRepo.findById(id);
        Review existingReview = existingReviewOptinal.get();
        existingReview.setStatus(!existingReview.isStatus());
        Review updateReview = reviewRepo.save(existingReview);
        return mapToDto(updateReview);

    }

    public ReviewDto createNewReview(ReviewDto newReviewDto) {
        newReviewDto.setDatesubmited(new Date());
        Review newReview = mapToModel(newReviewDto);
        Review savedReview = reviewRepo.save(newReview);
        return mapToDto(savedReview);
    }

    public ReviewDto editReview(int id, ReviewDto updatedReviewDto) {
        try {
            // Fetch existing review
            Review existingReview = reviewRepo.findById(id).orElseThrow(() -> new Exception("Review not found with ID: " + id));

            // Map updated fields to model
            existingReview.setDatesubmited(new Date());
            existingReview.setReviewtext(updatedReviewDto.getReviewtext());
            existingReview.setRating(updatedReviewDto.getRating());
            // ... Update other fields as needed based on your ReviewDto properties

            // Save updated review
            Review savedReview = reviewRepo.save(existingReview);
            return mapToDto(savedReview);
        } catch (Exception e) {
            // Handle potential exceptions
            throw new RuntimeException("Error updating review", e);
        }
    }

    public String getUsernameByUserId(int userId) {
        Optional<Users> userOptional = userRepo.findById(userId);
        return userOptional.map(Users::getUsername).orElse(null);
    }

    public String getRoomNoByRoomId(int roomId) {
        Optional<Room> roomOptional = roomRepo.findById(roomId);
        return roomOptional.map(Room::getRoom_no).orElse(null);
    }

}
