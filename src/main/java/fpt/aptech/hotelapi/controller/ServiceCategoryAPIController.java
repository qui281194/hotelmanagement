/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.ServiceCategoryDto;
import fpt.aptech.hotelapi.models.ServiceCategory;

import fpt.aptech.hotelapi.service.ServiceDV;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/servicecategorycontroller")
public class ServiceCategoryAPIController {
     @Autowired
    private ServiceDV serviceDV;
     
     // Lấy danh sách tất cả các loại dịch vụ
    @GetMapping("/all")
    public ResponseEntity<List<ServiceCategoryDto>> function_getAllServiceCategories() {
        List<ServiceCategoryDto> categories = serviceDV.getAllServiceCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Tạo mới một loại dịch vụ
    @PostMapping("/create")
    public ResponseEntity<ServiceCategoryDto> function_createServiceCategory(@RequestBody ServiceCategoryDto categoryDto) {
        ServiceCategoryDto createdCategory = serviceDV.createServiceCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
    
    // Xóa một loại dịch vụ dựa trên ID
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> function_deleteServiceCategory(@PathVariable Integer categoryId) {
        serviceDV.deleteServiceCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Sửa thông tin một loại dịch vụ
    @PutMapping("/edit/{categoryId}")
    public ResponseEntity<ServiceCategoryDto> function_updateServiceCategory(@PathVariable Integer categoryId,
                                                                   @RequestBody ServiceCategoryDto updatedCategoryDto) {
        ServiceCategoryDto updatedCategory = serviceDV.updateServiceCategory(categoryId, updatedCategoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    // Tìm kiếm loại dịch vụ theo tên (không phân biệt chữ hoa/chữ thường)
    @GetMapping("/search")
    public List<ServiceCategory> findServiceCategoriesByName(@RequestParam String name) {
        // Sử dụng phương thức findByName từ ServiceCategoryRepository
        return serviceDV.searchServiceCategoriesByName(name);
    }
}
