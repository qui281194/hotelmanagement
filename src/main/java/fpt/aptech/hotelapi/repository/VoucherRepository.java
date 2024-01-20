/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package fpt.aptech.hotelapi.repository;


import fpt.aptech.hotelapi.dto.VoucherDto;
import fpt.aptech.hotelapi.models.Voucher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

    public Voucher save(VoucherDto newVoucherDto);

    public VoucherDto findAllById(int id);

     List<Voucher> findByTitleContainingIgnoreCase(String title);
    
}
