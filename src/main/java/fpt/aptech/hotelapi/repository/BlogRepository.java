/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.Blog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ASUS
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {
     // Tìm kiếm dịch vụ theo tên (không phân biệt chữ hoa/chữ thường) - Custom query
    @Query("SELECT s FROM Blog s WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Blog> findByTitleContaining(@Param("title") String title);
}
