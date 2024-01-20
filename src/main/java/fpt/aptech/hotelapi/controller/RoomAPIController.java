/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.RoomDto;
import fpt.aptech.hotelapi.service.RoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author TuanNguyen
 */
@RestController
@RequestMapping("/api/roomcontroller")
public class RoomAPIController {
    private RoomService _roomService;

    @Autowired
    public RoomAPIController(RoomService _roomService) {
        this._roomService = _roomService;
    }
    
    @GetMapping("/all")
    public List<RoomDto> function_allRoom() {
        return _roomService.allRoom();
    }
    
    @GetMapping("/allroomvacancy")
    public List<RoomDto> function_allRoomVacancy() {
        return _roomService.allRoomVacancy();
    }
    
    @GetMapping("/find/{roomId}")
    public RoomDto function_findRoom(@PathVariable("roomId") int roomId) {
        return _roomService.findRoom(roomId);
    }
    
    @PostMapping("/create")
    public RoomDto function_createNewRoom(@RequestBody RoomDto newRoomDto) {
        return _roomService.createNewRoom(newRoomDto);
    }
    
    @PutMapping("/update")
    public RoomDto function_updateRoom(@RequestBody RoomDto updateRoomDto) {
        return _roomService.updateRoom(updateRoomDto);
    }
}
