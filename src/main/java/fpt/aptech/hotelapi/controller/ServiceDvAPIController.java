/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.ServiceCategoryDto;
import fpt.aptech.hotelapi.models.Servicedv;
import fpt.aptech.hotelapi.service.DVService;
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
    private DVService dvService;
  
   @GetMapping("/all")
    public ResponseEntity<List<Servicedv>> getAllServices() {
        List<Servicedv> services = dvService.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<ServiceCategoryDto>> getAllCategories() {
        List<ServiceCategoryDto> categories = dvService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
   
// 
    @PostMapping("/create")
    public Servicedv function_createNewRoomType(@RequestBody Servicedv newdvService) {
        return dvService.saveImage(newdvService);
    }
    // Xóa dịch vụ theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        try {
            dvService.deleteById(id);
            return new ResponseEntity<>("Xóa dịch vụ thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xóa dịch vụ thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/edit/{id}")
public ResponseEntity<Servicedv> getEditService(@PathVariable Integer id) {
    Servicedv serviceToEdit = dvService.getServiceDetails(id);
    if (serviceToEdit != null) {
        return new ResponseEntity<>(serviceToEdit, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    // Chỉnh sửa thông tin dịch vụ
    @PutMapping("/edit/{id}")
    public ResponseEntity<Servicedv> editService(@PathVariable Integer id, @RequestBody Servicedv editedService) {
        Servicedv updatedService = dvService.editService(id, editedService);
        if (updatedService != null) {
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Servicedv>> searchServices(@RequestParam("name") String name) {
        List<Servicedv> searchedServices = dvService.searchServices(name);
        return new ResponseEntity<>(searchedServices, HttpStatus.OK);
    }

   
}

