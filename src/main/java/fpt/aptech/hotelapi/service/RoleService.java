/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.RoleDto;
import fpt.aptech.hotelapi.models.Role;
import fpt.aptech.hotelapi.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class RoleService {
    private RoleRepository _roleRepo;

    @Autowired
    public RoleService(RoleRepository _roleRepo) {
        this._roleRepo = _roleRepo;
    }
    
    private Role mapToModel(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleName(roleDto.getRole_name());
        
        return role;
    }
    
    private RoleDto mapToDto(Role role) {
        if(role == null) {
            return null;
        }
        
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole_name(role.getRoleName());
        return roleDto;
    }
    
    public List<RoleDto> getAllRole() {
        return _roleRepo.findAll().stream().map(role -> mapToDto(role)).collect(Collectors.toList());
    }
    
    public RoleDto findRole(int roleId) {
        return mapToDto(_roleRepo.findById(roleId).orElse(null));
    }
    
    public RoleDto createNewRole(RoleDto newRoleDto) {
        Role newRole = mapToModel(newRoleDto);
        
        Role responseRole = _roleRepo.save(newRole);
        
        return mapToDto(responseRole);
    }
}
