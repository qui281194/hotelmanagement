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
 * @author ASUS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicedvDto {
    private Integer id;
    private String name;
    private double price;
    private String description;
    private Integer quantity;
    private Integer serviceDuration;
    private String status;
    private String image;

    // ServiceCategoryDto thay vì ServiceCategory để tránh vòng lặp khi chuyển đổi
    private ServiceCategoryDto category;

    
    
}
