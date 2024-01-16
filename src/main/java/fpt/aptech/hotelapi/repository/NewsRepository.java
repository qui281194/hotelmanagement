/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;

import fpt.aptech.hotelapi.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface NewsRepository extends JpaRepository<News, Integer> {
    
}
