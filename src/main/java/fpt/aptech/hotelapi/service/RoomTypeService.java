/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.RoomTypeDto;
import fpt.aptech.hotelapi.models.RoomType;
import fpt.aptech.hotelapi.repository.RoomTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TuanNguyen
 */
@Service
public class RoomTypeService {
    private RoomTypeRepository _roomTypeRepo;

    @Autowired
    public RoomTypeService(RoomTypeRepository _roomTypeRepo) {
        this._roomTypeRepo = _roomTypeRepo;
    }
    
    private RoomTypeDto mapToDto(RoomType roomType) {
        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setId(roomType.getId());
        roomTypeDto.setRoom_type_name(roomType.getRoom_type_name());
        return roomTypeDto;
    }
    
    public List<RoomTypeDto> allRoomType() {
        return _roomTypeRepo.findAll()
                .stream()
                .map(mapper -> mapToDto(mapper))
                .toList();
    }
    
}
