/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.Servicedv;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ASUS
 */
public interface ServicedvRepository extends JpaRepository<Servicedv, Integer> {
    List<Servicedv> findByCategoryId(Integer categoryId);
     
    // Tìm kiếm dịch vụ theo tên (không phân biệt chữ hoa/chữ thường) - Custom query
    @Query("SELECT s FROM Servicedv s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Servicedv> searchByNameIgnoreCaseContaining(@Param("name") String name);
}
