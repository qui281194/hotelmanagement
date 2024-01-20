/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.hotelapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TuanNguyen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCurrentDto {
    private int id;
    private String booking_current_name;
}
