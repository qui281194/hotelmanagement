/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.ServicedvDto;
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
@RequestMapping("/api/servicedvcontroller")
public class ServiceDvAPIController {
    @Autowired
    private ServiceDV serviceDV;

    public ServiceDvAPIController(ServiceDV serviceDV) {
        this.serviceDV = serviceDV;
    }
    
    
    // Lấy danh sách tất cả các dịch vụ
    @GetMapping("/all")
    public ResponseEntity<List<ServicedvDto>> function_getAllServices() {
        List<ServicedvDto> services = serviceDV.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    
     // Tạo mới một dịch vụ
    @PostMapping("/create")
    public ResponseEntity<ServicedvDto> function_createService(@RequestBody ServicedvDto serviceDto) {
        ServicedvDto createdService = serviceDV.createService(serviceDto);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }
    
    // Xóa một dịch vụ dựa trên ID
    @DeleteMapping("/delete/{serviceId}")
    public ResponseEntity<Void> function_deleteService(@PathVariable Integer serviceId) {
        serviceDV.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Sửa thông tin một dịch vụ
    @PutMapping("/edit/{serviceId}")
    public ResponseEntity<ServicedvDto> function_updateService(@PathVariable Integer serviceId,
                                                      @RequestBody ServicedvDto updatedServiceDto) {
        ServicedvDto updatedService = serviceDV.updateService(serviceId, updatedServiceDto);
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }

    // Tìm kiếm dịch vụ theo tên
    @GetMapping("/services/search")
    public ResponseEntity<List<ServicedvDto>> function_searchServicesByName(@RequestParam String name) {
        List<ServicedvDto> searchedServices = serviceDV.searchServicesByName(name);
        return new ResponseEntity<>(searchedServices, HttpStatus.OK);
    }

   
}

