/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.RoleDto;
import fpt.aptech.hotelapi.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/roles")
public class RoleRestController {
    private RoleService _roleService;
    
    @Autowired
    public RoleRestController(RoleService _roleService) {
        this._roleService = _roleService;
    }
    
    @GetMapping("/all")
    public List<RoleDto> function_getAllRole() {
        return _roleService.getAllRole();
    }
    
    @GetMapping("/find/{id}")
    public RoleDto function_findRole(@PathVariable("id") int roleId) {
        return _roleService.findRole(roleId);
    }
    
    @PostMapping("/create")
    public RoleDto function_createNewRole(@RequestBody RoleDto newRoleDto) {
        return _roleService.createNewRole(newRoleDto);
    }
}
