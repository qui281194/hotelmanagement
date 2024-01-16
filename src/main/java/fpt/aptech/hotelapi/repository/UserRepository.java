/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);
}
