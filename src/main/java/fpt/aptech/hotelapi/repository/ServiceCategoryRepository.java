/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.ServiceCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



/**
 *
 * @author ASUS
 */
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
  
    // Tìm kiếm ServiceCategory theo tên
    @Query("SELECT s FROM ServiceCategory s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<ServiceCategory> findByName(@Param("name")String name);
}
