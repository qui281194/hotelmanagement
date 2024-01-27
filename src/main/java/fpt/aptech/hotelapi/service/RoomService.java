/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.BookingStatusDto;
import fpt.aptech.hotelapi.dto.RoomDto;
import fpt.aptech.hotelapi.dto.RoomTypeDto;
import fpt.aptech.hotelapi.models.BookingStatus;
import fpt.aptech.hotelapi.models.Room;
import fpt.aptech.hotelapi.models.RoomType;
import fpt.aptech.hotelapi.repository.BookingStatusRepository;
import fpt.aptech.hotelapi.repository.RoomRepository;
import fpt.aptech.hotelapi.repository.RoomTypeRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TuanNguyen
 */
@Service
public class RoomService {
    private RoomTypeRepository _roomTypeRepo;
    private BookingStatusRepository _bookingStatusRepo;
    private RoomRepository _roomRepo;

    @Autowired
    public RoomService(RoomTypeRepository _roomTypeRepo, BookingStatusRepository _bookingStatusRepo, RoomRepository _roomRepo) {
        this._roomTypeRepo = _roomTypeRepo;
        this._bookingStatusRepo = _bookingStatusRepo;
        this._roomRepo = _roomRepo;
    }
    
    private Room mapToModel(RoomDto roomDto) {
        Room room = new Room();
        room.setRoom_no(roomDto.getRoom_no());
        room.setRoom_price(roomDto.getRoom_price());
        room.setRoom_image(roomDto.getRoom_image());
        room.setRoom_capacity(roomDto.getRoom_capacity());
        room.setRoom_description(roomDto.getRoom_description());
        room.setIs_active(roomDto.getIs_active());
        
        BookingStatus bookingStatusInfo = _bookingStatusRepo.findById(roomDto.getBooking_status_id()).orElse(null);
        RoomType roomTypeInfo = _roomTypeRepo.findById(roomDto.getRoom_type_id()).orElse(null);
        
        room.setBooking_status_id(bookingStatusInfo);
        room.setRoom_type_id(roomTypeInfo);
        
        return room;
    }
    
    private RoomDto mapToDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoom_no(room.getRoom_no());
        roomDto.setRoom_price(room.getRoom_price());
        roomDto.setRoom_image(room.getRoom_image());
        roomDto.setRoom_capacity(room.getRoom_capacity());
        roomDto.setRoom_description(room.getRoom_description());
        roomDto.setIs_active(room.getIs_active());
        
        BookingStatusDto bookingStatusDto = new BookingStatusDto();
        bookingStatusDto.setId(room.getBooking_status_id().getId());
        bookingStatusDto.setBooking_status_name(room.getBooking_status_id().getBooking_status_name());
        
        roomDto.setBooking_status_id(bookingStatusDto.getId());
        roomDto.setBooking_status_info(bookingStatusDto);
        
        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setId(room.getRoom_type_id().getId());
        roomTypeDto.setRoom_type_name(room.getRoom_type_id().getRoom_type_name());
        
        roomDto.setRoom_type_id(roomTypeDto.getId());
        roomDto.setRoom_type_info(roomTypeDto);
        
        return roomDto;
    }
    
    public List<RoomDto> allRoom() {
        return _roomRepo.findAll().stream()
                .map(mapper -> mapToDto(mapper))
                .collect(Collectors.toList());
    }
    
    public List<RoomDto> allRoomVacancy() {
        return _roomRepo.findAll().stream()
                .filter(r -> r.getBooking_status_id().getId() == 1)
                .map(mapper -> mapToDto(mapper))
                .collect(Collectors.toList());
    }
    
    public List<RoomDto> allRoomSortedByActive() {
        return _roomRepo.findAll().stream()
                .sorted((p1 , p2) -> -p1.getIs_active().compareTo(p2.getIs_active()))
                .map(mapper -> mapToDto(mapper))
                .collect(Collectors.toList());
    }
    
    public List<RoomDto> allRoomActiveAndVancancy() {
        return _roomRepo.findAll().stream()
                .filter(r -> r.getBooking_status_id().getId() == 1 && r.getIs_active() == true)
                .map(mapper -> mapToDto(mapper))
                .collect(Collectors.toList());
    }
    
    public RoomDto findRoom(int roomId) {
        Room roomInfo = _roomRepo.findById(roomId).orElse(null);
        return mapToDto(roomInfo);
    }
    
    public RoomDto createNewRoom(RoomDto newRoomDto) {
        newRoomDto.setBooking_status_id(1);
        Room newRoom = mapToModel(newRoomDto);
        
        Room response = _roomRepo.save(newRoom);
        
        return mapToDto(response);
    }
    
    public RoomDto updateRoom(RoomDto updateRoomDto) {
        Room updateRoom = mapToModel(updateRoomDto);
        updateRoom.setId(updateRoomDto.getId());
        
        Room response = _roomRepo.save(updateRoom);
        
        return mapToDto(response);
    }
}
