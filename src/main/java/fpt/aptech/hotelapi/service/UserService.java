/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.LoginDto;
import fpt.aptech.hotelapi.dto.RoleDto;
import fpt.aptech.hotelapi.dto.UserDto;
import fpt.aptech.hotelapi.models.Role;
import fpt.aptech.hotelapi.models.Users;
import fpt.aptech.hotelapi.repository.RoleRepository;
import fpt.aptech.hotelapi.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class UserService {

    private UserRepository userRepo;
    private RoleRepository roleRepo;

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    private Users mapToModel(UserDto userDto) {
        Users users = new Users();
        users.setUsername(userDto.getUsername());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        users.setAddress(userDto.getAddress());
        users.setPhone(userDto.getPhone());

        Role roleInfo = roleRepo.findById(userDto.getRole_id()).orElse(null);
        users.setRole_id(roleInfo);

        return users;
    }

    private UserDto mapToDto(Users users) {
        UserDto userDto = new UserDto();
        userDto.setId(users.getId());
        userDto.setUsername(users.getUsername());
        userDto.setEmail(users.getEmail());
        userDto.setPassword(users.getPassword());
        userDto.setAddress(users.getAddress());
        userDto.setPhone(users.getPhone());

        userDto.setRole_id(users.getRole_id().getId());

        RoleDto roleDto = new RoleDto(
                users.getRole_id().getId(),
                users.getRole_id().getRoleName()
        );
        userDto.setRoleInfo(roleDto);

        return userDto;
    }

    //Login Service
    public UserDto login(LoginDto loginDto) {
        Optional<Users> optionalUser = userRepo.findByEmail(loginDto.getEmail());
        if (optionalUser.isPresent()) {
            Users checkLogin = optionalUser.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(loginDto.getPassword(), checkLogin.getPassword())) {
                return mapToDto(checkLogin);
            }
        }
        return null;
    }

    //For Admin ONLY
    public UserDto createNewUser(UserDto userDto) {
        //Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
        if (userRepo.existsByEmail(userDto.getEmail())) {
            return null; // Trả về null nếu email đã tồn tại
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        Users newUser = mapToModel(userDto);

        Users responseUser = userRepo.save(newUser);

        return mapToDto(responseUser);
    }

    public UserDto updateUser(UserDto updatedUserDto) {
        Optional<Users> optionalUser = userRepo.findById(updatedUserDto.getId());
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();

            // Update the user information
            existingUser.setUsername(updatedUserDto.getUsername());
            existingUser.setEmail(updatedUserDto.getEmail());
            existingUser.setAddress(updatedUserDto.getAddress());
            existingUser.setPhone(updatedUserDto.getPhone());

            Users updatedUser = userRepo.save(existingUser);
            return mapToDto(updatedUser);
        }
        return null;
    }

//    public boolean deleteById(int id) {
//        Optional<Users> optionalUser = userRepo.findById(id);
//        if (optionalUser.isPresent()) {
//            userRepo.deleteById(id);
//            return true;
//        }
//        return false;
//    }
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }

    public List<UserDto> allUser() {
        List<Users> users = userRepo.findAll();
        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public UserDto findOne(int id) {
        Optional<Users> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            return mapToDto(user);
        }
        return null;
    }

    public boolean changePassword(int id, String currentPassword, String newPassword
    ) {
        Optional<Users> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                String encodedNewPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encodedNewPassword);
                userRepo.save(user);
                return true;
            }
        }
        return false;

    }

    //For Staff
    //For Customer
    public UserDto registerNewCustomer(UserDto userDto) {
        // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
        if (userRepo.existsByEmail(userDto.getEmail())) {
            return null; // Trả về null nếu email đã tồn tại
        }
        userDto.setRole_id(3);

        //Mã hóa mật khẩu trước khi lưu trữ vào cơ sở dữ liệu
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        Users newCustomer = mapToModel(userDto);

        Users responseUser = userRepo.save(newCustomer);

        return mapToDto(responseUser);
    }

    //Kiểm tra trùng email
    public boolean existsByEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }
}
