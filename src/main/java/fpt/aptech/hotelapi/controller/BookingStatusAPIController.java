/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.BookingStatusDto;
import fpt.aptech.hotelapi.service.BookingStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author TuanNguyen
 */
@RestController
@RequestMapping("/api/bookingstatuscontroller")
public class BookingStatusAPIController {
    private BookingStatusService _bookingStatusService;

    @Autowired
    public BookingStatusAPIController(BookingStatusService _bookingStatusService) {
        this._bookingStatusService = _bookingStatusService;
    }
    
    @GetMapping("/all")
    public List<BookingStatusDto> function_allBookingStatus() {
        return _bookingStatusService.allBookingStatus();
    }
    
    @GetMapping("/findBookingStatusById/{id}")
    public BookingStatusDto function_findBookingStatusById(@PathVariable("id") int id) {
        return _bookingStatusService.findBookingStatusById(id);
    }
    
    @GetMapping("/findBookingStatusByName/{name}")
    public BookingStatusDto function_findBookingStatusByName(@PathVariable("name") String name) {
        return _bookingStatusService.findBookingStatusByName(name);
    }
}
