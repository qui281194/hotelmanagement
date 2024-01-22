/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.BookingStatusDto;
import fpt.aptech.hotelapi.models.BookingStatus;
import fpt.aptech.hotelapi.repository.BookingStatusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TuanNguyen
 */
@Service
public class BookingStatusService {
    private BookingStatusRepository _bookingStatusRepo;

    @Autowired
    public BookingStatusService(BookingStatusRepository _bookingStatusRepo) {
        this._bookingStatusRepo = _bookingStatusRepo;
    }
    
    private BookingStatusDto mapToDto(BookingStatus bookingStatus) {
        BookingStatusDto bookingStatusDto = new BookingStatusDto();
        bookingStatusDto.setId(bookingStatus.getId());
        bookingStatusDto.setBooking_status_name(bookingStatus.getBooking_status_name());
        
        return bookingStatusDto;
    }
    
    public List<BookingStatusDto> allBookingStatus() {
        return _bookingStatusRepo.findAll()
                .stream()
                .map(mapper -> mapToDto(mapper))
                .toList();
    }
    
    public BookingStatusDto findBookingStatusById(int id) {
        return _bookingStatusRepo.findById(id).map(mapper -> mapToDto(mapper)).orElse(null);
    }
    
    public BookingStatusDto findBookingStatusByName(String name) {
        return _bookingStatusRepo.findAll()
                .stream()
                .filter(b -> b.getBooking_status_name().equals(name))
                .map(mapper -> mapToDto(mapper))
                .findFirst().orElse(null);
    }
}
