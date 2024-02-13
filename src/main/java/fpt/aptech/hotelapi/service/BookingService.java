/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.BookingCurrentDto;
import fpt.aptech.hotelapi.dto.BookingDto;
import fpt.aptech.hotelapi.dto.BookingStatusDto;
import fpt.aptech.hotelapi.dto.RoleDto;
import fpt.aptech.hotelapi.dto.RoomDto;
import fpt.aptech.hotelapi.dto.RoomTypeDto;
import fpt.aptech.hotelapi.dto.SearchDto;
import fpt.aptech.hotelapi.dto.UserDto;
import fpt.aptech.hotelapi.models.Booking;
import fpt.aptech.hotelapi.models.BookingCurrent;
import fpt.aptech.hotelapi.models.BookingStatus;
import fpt.aptech.hotelapi.models.Room;
import fpt.aptech.hotelapi.models.Users;
import fpt.aptech.hotelapi.repository.BookingCurrentRepository;
import fpt.aptech.hotelapi.repository.BookingRepository;
import fpt.aptech.hotelapi.repository.BookingStatusRepository;
import fpt.aptech.hotelapi.repository.RoomRepository;
import fpt.aptech.hotelapi.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TuanNguyen
 */
@Service
public class BookingService {

    private BookingCurrentRepository _bookingCurrentRepo;
    private BookingStatusRepository _bookingStatusRepo;
    private BookingRepository _bookingRepo;
    private RoomRepository _roomRepo;
    private UserRepository _userRepo;

    @Autowired
    public BookingService(BookingCurrentRepository _bookingCurrentRepo,
            BookingStatusRepository _bookingStatusRepo,
            BookingRepository _bookingRepo,
            RoomRepository _roomRepo,
            UserRepository _userRepo) {
        this._bookingCurrentRepo = _bookingCurrentRepo;
        this._bookingStatusRepo = _bookingStatusRepo;
        this._bookingRepo = _bookingRepo;
        this._roomRepo = _roomRepo;
        this._userRepo = _userRepo;
    }

    private Booking mapToModel(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setBooking_from(bookingDto.getBooking_from());
        booking.setBooking_to(bookingDto.getBooking_to());

        int total_day = bookingDto.getBooking_to().getDayOfMonth() - bookingDto.getBooking_from().getDayOfMonth();
        booking.setTotal_day(total_day);

        Room roomInfo = _roomRepo.findById(bookingDto.getRoom_id()).orElse(null);
        double total_price = total_day * roomInfo.getRoom_price();
        booking.setTotal_price(total_price);

        booking.setNumber_of_member(bookingDto.getNumber_of_member());

        booking.setIs_active(bookingDto.getIs_active());

        BookingCurrent bookingCurrent = _bookingCurrentRepo.findById(bookingDto.getBooking_current_id()).orElse(null);
        booking.setBooking_current_id(bookingCurrent);

        Users customerInfo = _userRepo.findById(bookingDto.getCustomer_id()).orElse(null);
        booking.setCustomer_id(customerInfo);

        booking.setRoom_id(roomInfo);

        return booking;
    }

    private BookingDto mapToDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());
        bookingDto.setBooking_from(booking.getBooking_from());
        bookingDto.setBooking_to(booking.getBooking_to());
        bookingDto.setTotal_day(booking.getTotal_day());
        bookingDto.setTotal_price(booking.getTotal_price());
        bookingDto.setNumber_of_member(booking.getNumber_of_member());
        bookingDto.setIs_active(booking.getIs_active());

        bookingDto.setBooking_current_id(booking.getBooking_current_id().getId());
        bookingDto.setBooking_current_info(new BookingCurrentDto(booking.getBooking_current_id().getId(), booking.getBooking_current_id().getBooking_current_name()));

        bookingDto.setCustomer_id(booking.getCustomer_id().getId());
        UserDto userDto = new UserDto();
        userDto.setId(booking.getCustomer_id().getId());
        userDto.setUsername(booking.getCustomer_id().getUsername());
        userDto.setPassword(booking.getCustomer_id().getPassword());
        userDto.setEmail(booking.getCustomer_id().getEmail());
        userDto.setAddress(booking.getCustomer_id().getAddress());
        userDto.setPhone(booking.getCustomer_id().getPhone());
        userDto.setRole_id(booking.getCustomer_id().getRole_id().getId());
        userDto.setRoleInfo(new RoleDto(booking.getCustomer_id().getRole_id().getId(), booking.getCustomer_id().getRole_id().getRoleName()));
        bookingDto.setCustomer_info(userDto);

        bookingDto.setRoom_id(booking.getRoom_id().getId());
        RoomDto roomDto = new RoomDto();
        roomDto.setId(booking.getRoom_id().getId());
        roomDto.setRoom_no(booking.getRoom_id().getRoom_no());
        roomDto.setRoom_price(booking.getRoom_id().getRoom_price());
        roomDto.setRoom_image(booking.getRoom_id().getRoom_image());
        roomDto.setRoom_capacity(booking.getRoom_id().getRoom_capacity());
        roomDto.setRoom_description(booking.getRoom_id().getRoom_description());
        roomDto.setIs_active(booking.getRoom_id().getIs_active());

        roomDto.setBooking_status_id(booking.getRoom_id().getBooking_status_id().getId());
        roomDto.setBooking_status_info(new BookingStatusDto(booking.getRoom_id().getBooking_status_id().getId(), booking.getRoom_id().getBooking_status_id().getBooking_status_name()));

        roomDto.setRoom_type_id(booking.getRoom_id().getRoom_type_id().getId());
        roomDto.setRoom_type_info(new RoomTypeDto(booking.getRoom_id().getRoom_type_id().getId(), booking.getRoom_id().getRoom_type_id().getRoom_type_name()));

        bookingDto.setRoom_info(roomDto);

        return bookingDto;
    }

    private RoomDto mapToRoomDto(Room room) {
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

    public List<BookingDto> allBooking() {
        return _bookingRepo.findAll().stream()
                .map(mapper -> mapToDto(mapper)).collect(Collectors.toList());
    }
    
    public List<BookingDto> allBookingByCustomer(int customerId) {
        return _bookingRepo.findAll().stream()
                .filter(b -> b.getCustomer_id().getId() == customerId)
                .map(mapper -> mapToDto(mapper))
                .toList();
    }

    public BookingDto createNewBooking(BookingDto newBookingDto) {
        BookingStatus bookingStatusReserved = _bookingStatusRepo.findById(2).orElse(null);
        Room roomUpdate = _roomRepo.findById(newBookingDto.getRoom_id()).orElse(null);
        roomUpdate.setBooking_status_id(bookingStatusReserved);
        _roomRepo.save(roomUpdate);

        newBookingDto.setBooking_current_id(1);
        Booking newBooking = mapToModel(newBookingDto);

        Booking response = _bookingRepo.save(newBooking);

        return mapToDto(response);
    }

    public BookingDto confirmBookingDetailByCustomer(BookingDto newBookingDto) {
        List<BookingDto> findingBooking = _bookingRepo.findAll()
                .stream()
                .filter(b -> (b.getRoom_id().getId() == newBookingDto.getRoom_id())
                && ((newBookingDto.getBooking_from().isEqual(b.getBooking_from()))
                || (newBookingDto.getBooking_from().isEqual(b.getBooking_to()))
                || (newBookingDto.getBooking_to().isEqual(b.getBooking_from()))
                || (newBookingDto.getBooking_to().isEqual(b.getBooking_to()))
                || (newBookingDto.getBooking_from().isBefore(b.getBooking_from()))
                || (newBookingDto.getBooking_from().isAfter(b.getBooking_from()) && newBookingDto.getBooking_from().isBefore(b.getBooking_to()))
                || (newBookingDto.getBooking_to().isBefore(b.getBooking_from()))
                || (newBookingDto.getBooking_to().isAfter(b.getBooking_from()) && newBookingDto.getBooking_to().isBefore(b.getBooking_to())))
                )
                .map(mapper -> mapToDto(mapper)).toList();
        if (findingBooking.isEmpty() == false) {
            return null;
        } 
        else {
            Booking bookingInfo = new Booking();
            bookingInfo.setBooking_from(newBookingDto.getBooking_from());
            bookingInfo.setBooking_to(newBookingDto.getBooking_to());
            bookingInfo.setIs_active(true);
            bookingInfo.setNumber_of_member(newBookingDto.getNumber_of_member());
            int total_day = newBookingDto.getBooking_to().getDayOfMonth() - newBookingDto.getBooking_from().getDayOfMonth();
            bookingInfo.setTotal_day(total_day);
            Room roomInfo = _roomRepo.findById(newBookingDto.getRoom_id()).orElse(null);
            bookingInfo.setRoom_id(roomInfo);
            Users customerInfo = _userRepo.findById(newBookingDto.getCustomer_id()).orElse(null);
            bookingInfo.setCustomer_id(customerInfo);
            double total_price = total_day * roomInfo.getRoom_price();
            bookingInfo.setTotal_price(total_price);
            bookingInfo.setBooking_current_id(_bookingCurrentRepo.findById(1).orElse(null));
            
            return mapToDto(bookingInfo);
        }
    }
    
    public BookingDto createNewBookingByCustomer(BookingDto newBookingDto) {
        BookingStatus bookingStatusReserved = _bookingStatusRepo.findById(2).orElse(null);
        Room roomUpdate = _roomRepo.findById(newBookingDto.getRoom_id()).orElse(null);
        roomUpdate.setBooking_status_id(bookingStatusReserved);
        _roomRepo.save(roomUpdate);

        newBookingDto.setBooking_current_id(1);
        Booking newBooking = mapToModel(newBookingDto);

        Booking response = _bookingRepo.save(newBooking);

        return mapToDto(response);
    }

    public BookingDto confirmBooking(int bookingId) {
        Booking bookingInfoToConfirm = _bookingRepo.findById(bookingId).orElse(null);
        BookingCurrent confirm = _bookingCurrentRepo.findAll()
                .stream().filter(b -> b.getBooking_current_name().equals("CONFIRMED"))
                .findFirst().orElse(null);
        bookingInfoToConfirm.setBooking_current_id(confirm);

        Booking response = _bookingRepo.save(bookingInfoToConfirm);

        return mapToDto(response);
    }

    public BookingDto checkInBooking(int bookingId) {
        Booking bookingInfoToOperational = _bookingRepo.findById(bookingId).orElse(null);
        BookingCurrent operational = _bookingCurrentRepo.findAll()
                .stream().filter(b -> b.getBooking_current_name().equals("OPERATIONAL"))
                .findFirst().orElse(null);
        bookingInfoToOperational.setBooking_current_id(operational);

        BookingStatus occupiedStatus = _bookingStatusRepo.findAll()
                .stream().filter(b -> b.getBooking_status_name().equals("OCCUPIED"))
                .findFirst().orElse(null);
        Room roomInfo = bookingInfoToOperational.getRoom_id();
        roomInfo.setBooking_status_id(occupiedStatus);
        _roomRepo.save(roomInfo);

        Booking response = _bookingRepo.save(bookingInfoToOperational);

        return mapToDto(response);
    }

    public BookingDto checkOutBooking(int bookingId) {
        Booking bookingInfoToComplete = _bookingRepo.findById(bookingId).orElse(null);
        BookingCurrent completed = _bookingCurrentRepo.findAll()
                .stream().filter(b -> b.getBooking_current_name().equals("COMPLETED"))
                .findFirst().orElse(null);
        bookingInfoToComplete.setBooking_current_id(completed);

        BookingStatus vacancyStatus = _bookingStatusRepo.findAll()
                .stream().filter(b -> b.getBooking_status_name().equals("VACANCY"))
                .findFirst().orElse(null);
        Room roomInfo = bookingInfoToComplete.getRoom_id();
        roomInfo.setBooking_status_id(vacancyStatus);
        _roomRepo.save(roomInfo);

        Booking response = _bookingRepo.save(bookingInfoToComplete);

        return mapToDto(response);
    }

    public BookingDto cancelBooking(int bookingId) {
        Booking bookingInfoToCancel = _bookingRepo.findById(bookingId).orElse(null);
        BookingCurrent canceled = _bookingCurrentRepo.findAll()
                .stream().filter(b -> b.getBooking_current_name().equals("CANCELED"))
                .findFirst().orElse(null);
        bookingInfoToCancel.setBooking_current_id(canceled);
        bookingInfoToCancel.setIs_active(false);

        BookingStatus vacancyStatus = _bookingStatusRepo.findAll()
                .stream().filter(b -> b.getBooking_status_name().equals("VACANCY"))
                .findFirst().orElse(null);
        Room roomInfo = bookingInfoToCancel.getRoom_id();
        roomInfo.setBooking_status_id(vacancyStatus);
        _roomRepo.save(roomInfo);

        Booking response = _bookingRepo.save(bookingInfoToCancel);

        return mapToDto(response);
    }

    //Search Room by Booking
    public List<RoomDto> searchAvailableRoomForBooking(SearchDto searchDto) {
        if (searchDto.getBooking_from() == null && searchDto.getBooking_to() == null) {
            if (searchDto.getRoom_type_id() == 0 && (searchDto.getFrom_price() != 0 && searchDto.getTo_price() != 0)) {
                List<RoomDto> allRoomAvailable = _roomRepo.findAll()
                        .stream()
                        .filter(r -> r.getRoom_price() >= searchDto.getFrom_price() && r.getRoom_price() <= searchDto.getTo_price())
                        .map(mapper -> mapToRoomDto(mapper))
                        .toList();
                return allRoomAvailable;
            } else if (searchDto.getRoom_type_id() != 0 && (searchDto.getFrom_price() != 0 && searchDto.getTo_price() != 0)) {
                List<RoomDto> allRoomAvailable = _roomRepo.findAll()
                        .stream()
                        .filter(r -> r.getRoom_price() >= searchDto.getFrom_price() && r.getRoom_price() <= searchDto.getTo_price() && r.getRoom_type_id().getId() == searchDto.getRoom_type_id())
                        .map(mapper -> mapToRoomDto(mapper))
                        .toList();
                return allRoomAvailable;
            } else {
                List<RoomDto> allRoomAvailable = _roomRepo.findAll()
                        .stream()
                        .filter(r -> r.getRoom_type_id().getId() == searchDto.getRoom_type_id())
                        .map(mapper -> mapToRoomDto(mapper))
                        .toList();
                return allRoomAvailable;
            }
        } else {
            if (searchDto.getRoom_type_id() == 0 && (searchDto.getFrom_price() != 0 && searchDto.getTo_price() != 0)) {
                List<BookingDto> allBookingHaveBeenBooked = _bookingRepo.findAll()
                        .stream()
                        .filter(b
                                -> (searchDto.getBooking_from().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_from().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_from().isAfter(b.getBooking_from()) && searchDto.getBooking_from().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_to().isAfter(b.getBooking_from()) && searchDto.getBooking_to().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_from().isBefore(b.getBooking_from()) && searchDto.getBooking_to().isAfter(b.getBooking_to()))
                        )
                        .map(mapper -> mapToDto(mapper))
                        .toList();

                System.out.println(allBookingHaveBeenBooked);

                List<RoomDto> allRoomBooked = new ArrayList<>();
                allBookingHaveBeenBooked.stream().forEach(b -> allRoomBooked.add(b.getRoom_info()));
                System.out.println(allRoomBooked);

                List<RoomDto> allRoomAvailable = _roomRepo.findAll()
                        .stream()
                        .filter(p -> (!allRoomBooked.stream().anyMatch(r -> r.getId() == p.getId())) && (p.getRoom_price() >= searchDto.getFrom_price() && p.getRoom_price() <= searchDto.getTo_price()))
                        .map(mapper -> mapToRoomDto(mapper))
                        .toList();

                return allRoomAvailable;
            } else if (searchDto.getRoom_type_id() != 0 && (searchDto.getFrom_price() != 0 && searchDto.getTo_price() != 0)) {
                List<BookingDto> allBookingHaveBeenBooked = _bookingRepo.findAll()
                        .stream()
                        .filter(b
                                -> (searchDto.getBooking_from().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_from().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_from().isAfter(b.getBooking_from()) && searchDto.getBooking_from().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_to().isAfter(b.getBooking_from()) && searchDto.getBooking_to().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_from().isBefore(b.getBooking_from()) && searchDto.getBooking_to().isAfter(b.getBooking_to()))
                        )
                        .map(mapper -> mapToDto(mapper))
                        .toList();

                System.out.println(allBookingHaveBeenBooked);

                List<RoomDto> allRoomBooked = new ArrayList<>();
                allBookingHaveBeenBooked.stream().forEach(b -> allRoomBooked.add(b.getRoom_info()));
                System.out.println(allRoomBooked);

                List<RoomDto> allRoomAvailable = _roomRepo.findAll()
                        .stream()
                        .filter(p -> (!allRoomBooked.stream().anyMatch(r -> r.getId() == p.getId())) && (p.getRoom_price() >= searchDto.getFrom_price() && p.getRoom_price() <= searchDto.getTo_price()) && (p.getRoom_type_id().getId() == searchDto.getRoom_type_id()))
                        .map(mapper -> mapToRoomDto(mapper))
                        .toList();

                return allRoomAvailable;
            } else if (searchDto.getRoom_type_id() == 0 && searchDto.getFrom_price() == 0 && searchDto.getTo_price() == 0) {
                List<BookingDto> allBookingHaveBeenBooked = _bookingRepo.findAll()
                        .stream()
                        .filter(b
                                -> (searchDto.getBooking_from().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_from().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_from().isAfter(b.getBooking_from()) && searchDto.getBooking_from().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_to().isAfter(b.getBooking_from()) && searchDto.getBooking_to().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_from().isBefore(b.getBooking_from()) && searchDto.getBooking_to().isAfter(b.getBooking_to()))
                        )
                        .map(mapper -> mapToDto(mapper))
                        .toList();

                System.out.println(allBookingHaveBeenBooked);

                List<RoomDto> allRoomBooked = new ArrayList<>();
                allBookingHaveBeenBooked.stream().forEach(b -> allRoomBooked.add(b.getRoom_info()));
                System.out.println(allRoomBooked);

                List<RoomDto> allRoomAvailable = _roomRepo.findAll()
                        .stream()
                        .filter(p -> !allRoomBooked.stream().anyMatch(r -> r.getId() == p.getId()))
                        .map(mapper -> mapToRoomDto(mapper))
                        .toList();

                return allRoomAvailable;
            } else {
                List<BookingDto> allBookingHaveBeenBooked = _bookingRepo.findAll()
                        .stream()
                        .filter(b
                                -> (searchDto.getBooking_from().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_from().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_from()))
                        || (searchDto.getBooking_to().isEqual(b.getBooking_to()))
                        || (searchDto.getBooking_from().isAfter(b.getBooking_from()) && searchDto.getBooking_from().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_to().isAfter(b.getBooking_from()) && searchDto.getBooking_to().isBefore(b.getBooking_to()))
                        || (searchDto.getBooking_from().isBefore(b.getBooking_from()) && searchDto.getBooking_to().isAfter(b.getBooking_to()))
                        )
                        .map(mapper -> mapToDto(mapper))
                        .toList();

                System.out.println(allBookingHaveBeenBooked);

                List<RoomDto> allRoomBooked = new ArrayList<>();
                allBookingHaveBeenBooked.stream().forEach(b -> allRoomBooked.add(b.getRoom_info()));
                System.out.println(allRoomBooked);

                List<RoomDto> allRoomAvailable = _roomRepo.findAll()
                        .stream()
                        .filter(p -> (!allRoomBooked.stream().anyMatch(r -> r.getId() == p.getId())) && (p.getRoom_type_id().getId() == searchDto.getRoom_type_id()))
                        .map(mapper -> mapToRoomDto(mapper))
                        .toList();

                return allRoomAvailable;
            }
        }
    }
}
