/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.RoomTypeDto;
import fpt.aptech.hotelapi.service.RoomTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author TuanNguyen
 */
@RestController
@RequestMapping("/api/roomtypecontroller")
public class RoomTypeAPIController {
    private RoomTypeService _roomTypeService;

    @Autowired
    public RoomTypeAPIController(RoomTypeService _roomTypeService) {
        this._roomTypeService = _roomTypeService;
    }
    
    @GetMapping("/all")
    public List<RoomTypeDto> function_allRoomType() {
        return _roomTypeService.allRoomType();
    }
}
