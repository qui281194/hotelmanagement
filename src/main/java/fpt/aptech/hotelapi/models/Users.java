/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpt.aptech.hotelapi.models;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author PC
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "active")
    private Boolean active;
    
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
    private Role role_id;
    
    @OneToMany(mappedBy = "customer_id" , cascade = CascadeType.ALL)
    private Collection<Booking> bookingCollection;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Review> reviews;
}
