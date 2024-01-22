/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author TuanNguyen
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    
}
