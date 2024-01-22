/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.BookingDto;
import fpt.aptech.hotelapi.service.BookingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author TuanNguyen
 */
@RestController
@RequestMapping("/api/bookingcontroller")
public class BookingAPIController {
    private BookingService _bookingService;

    @Autowired
    public BookingAPIController(BookingService _bookingService) {
        this._bookingService = _bookingService;
    }
    
    @GetMapping("/all")
    public List<BookingDto> function_allBooking() {
        return _bookingService.allBooking();
    }
    
    @GetMapping("/confirm/{id}")
    public BookingDto function_confirmBooking(@PathVariable("id") int id) {
        return _bookingService.confirmBooking(id);
    }
    
    @GetMapping("/checkin/{id}") 
    public BookingDto function_checkinBooking(@PathVariable("id") int id) {
        return _bookingService.checkInBooking(id);
    }
    
    @GetMapping("/checkout/{id}")
    public BookingDto function_checkoutBooking(@PathVariable("id") int id) {
        return _bookingService.checkOutBooking(id);
    }
    
    @GetMapping("/cancel/{id}")
    public BookingDto function_cancelBooking(@PathVariable("id") int id) {
        return _bookingService.cancelBooking(id);
    }
    
    
    @PostMapping("/create")
    public BookingDto function_createNewBooking(@RequestBody BookingDto newBookingDto) {
        return _bookingService.createNewBooking(newBookingDto);
    }
}
