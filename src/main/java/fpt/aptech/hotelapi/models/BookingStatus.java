/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.hotelapi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TuanNguyen
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_booking_status")
public class BookingStatus {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String booking_status_name;
    
    @OneToMany(mappedBy = "bookingStatus", cascade = CascadeType.ALL)
    private Collection<Room> roomCollection;
}
