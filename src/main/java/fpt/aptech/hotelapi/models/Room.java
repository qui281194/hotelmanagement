/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.hotelapi.models;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ASUS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    private String room_no;
    private Double room_price;
    
    private String room_image;
    private Integer room_capacity;
    private String room_description;
    private Boolean is_active;
   
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RoomType.class)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType room_type_id;
    
    @ManyToOne(fetch = FetchType.LAZY , targetEntity = BookingStatus.class)
    @JoinColumn(name = "booking_status_id", referencedColumnName = "id")
    private BookingStatus booking_status_id;
    
    @OneToMany(mappedBy = "room_id" , cascade = CascadeType.ALL)
    private Collection<Booking> bookingCollection;
    
    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    private List<Review> reviews;
}
