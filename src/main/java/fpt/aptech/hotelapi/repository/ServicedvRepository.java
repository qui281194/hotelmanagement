/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.Servicedv;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASUS
 */
public interface ServicedvRepository extends JpaRepository<Servicedv, Integer> {
    List<Servicedv> findByCategoryId(Integer categoryId);
     
    // Tìm kiếm dịch vụ theo tên (không phân biệt chữ hoa/chữ thường)
    List<Servicedv> findByNameIgnoreCaseContaining(String name);
}
