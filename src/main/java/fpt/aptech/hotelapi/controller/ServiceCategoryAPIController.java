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
    
    // Delete a service category by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteServiceCategory(@PathVariable Integer id) {
        serviceDV.deleteServiceCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // Xóa một loại dịch vụ dựa trên danh sách ID
    @DeleteMapping("/deleteMulti")
    public ResponseEntity<String> deleteAll(@RequestBody List<Integer> categoryIds) {
        try {
            if (categoryIds != null && !categoryIds.isEmpty()) {
                serviceDV.deleteConfig(categoryIds);
                return ResponseEntity.ok("Successfully deleted selected ServiceCategory");
            } else {
                return ResponseEntity.badRequest().body("ServiceCategory IDs list is empty");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
    // Lấy thông tin một loại dịch vụ theo ID
    @GetMapping("/edit/{id}")
    public ResponseEntity<ServiceCategoryDto> getServiceCategoryById(@PathVariable Integer id) {
        ServiceCategoryDto categoryDto = serviceDV.findByIdCategory(id);

        if (categoryDto != null) {
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Sửa thông tin một loại dịch vụ
    @PutMapping("/edit/{id}")
    public ResponseEntity<ServiceCategoryDto> function_updateServiceCategory(@PathVariable Integer id,
                                                                   @RequestBody ServiceCategoryDto updatedCategoryDto) {
        ServiceCategoryDto updatedCategory = serviceDV.updateServiceCategory(id, updatedCategoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    // Tìm kiếm loại dịch vụ theo tên (không phân biệt chữ hoa/chữ thường)
    @GetMapping("/search")
    public List<ServiceCategory> findServiceCategoriesByName(@RequestParam String name) {
        // Sử dụng phương thức findByName từ ServiceCategoryRepository
        return serviceDV.searchServiceCategoriesByName(name);
    }
}
