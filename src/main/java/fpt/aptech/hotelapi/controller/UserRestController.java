/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.LoginDto;
import fpt.aptech.hotelapi.dto.UserDto;
import fpt.aptech.hotelapi.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService _userService) {
        this.userService = _userService;
    }

    @GetMapping("/allstaff")
    public List<UserDto> allStaff() {
        return userService.allStaff();
    }
    
    @GetMapping("/allcustomer")
    public List<UserDto> allCustomer() {
        return userService.allCustomer();
    }
    
    @GetMapping("/findbyemail/{email}")
    public UserDto function_findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserDto> function_login(
            @RequestBody LoginDto loginDto
    ) {

        UserDto loggedInUser = userService.login(loginDto);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> function_allUser() {
        List<UserDto> users = userService.allUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserDto function_UserById(@PathVariable("id") int id) {
        return userService.findOne(id);
    }
    
    @GetMapping("/findbyid/{id}")
    public UserDto function_findUserById(@PathVariable("id") int id) {
        return userService.findOne(id);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> function_createNewUser(
            @RequestBody UserDto newUserDto
    ) {
        //        return _userService.createNewUser(newUserDto);
        UserDto createUser = userService.createNewUser(newUserDto);

        if (createUser != null) {
            return ResponseEntity.ok(createUser); //đăng ký thành công trả về OK
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // ngược lại email có r trả về CONFLICT
        }
    }
    
    @PostMapping("/createnewstaff")
    public UserDto function_createNewStaff(@RequestBody UserDto newStaffDto) {
//        System.out.println(newStaffDto);
        return userService.createNewUser(newStaffDto);
    }
    
    @PostMapping("/createnewcustomer")
    public UserDto function_createNewCustomer(@RequestBody UserDto newCustomerDto) {
        return userService.createNewUser(newCustomerDto);
    }
    
    

    @PostMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(
            @PathVariable int id,
            @RequestParam String currentPassword,
            @RequestParam String newPassword
    ) {
        boolean passwordChanged = userService.changePassword(id, currentPassword, newPassword);
        if (passwordChanged) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to change password. Please check your current password.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> function_registerNewCustomer(
            @RequestBody UserDto newUserDto
    ) {
        //        return _userService.registerNewCustomer(newUserDto);
        UserDto registeredUser = userService.registerNewCustomer(newUserDto);

        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser); //đăng ký thành công trả về OK
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // ngược lại email có r trả về CONFLICT
        }
    }
    
    @PostMapping("/createnewguest")
    public UserDto function_createNewGuest(@RequestBody UserDto newGuestDto) {
        return userService.registerNewGuest(newGuestDto);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> function_editUser(
            @PathVariable("id") int id,
            @RequestBody UserDto updatedUserDto
    ) {
        UserDto existingUser = userService.findOne(id);
        if (existingUser != null) {
            // Update the user information
            existingUser.setUsername(updatedUserDto.getUsername());
            existingUser.setEmail(updatedUserDto.getEmail());
            existingUser.setAddress(updatedUserDto.getAddress());
            existingUser.setPhone(updatedUserDto.getPhone());

            UserDto updatedUser = userService.updateUser(existingUser);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> function_deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
