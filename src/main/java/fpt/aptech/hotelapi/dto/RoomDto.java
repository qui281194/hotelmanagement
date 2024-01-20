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
public class RoomDto {
    private Integer id;
    private String room_no;
    private Double room_price;
    private String room_image;
    private Integer room_capacity;
    private String room_description;
    private Boolean is_active;
    
    private int booking_status_id;
    private BookingStatusDto booking_status_info;
    
    private int room_type_id;
    private RoomTypeDto room_type_info;
}
