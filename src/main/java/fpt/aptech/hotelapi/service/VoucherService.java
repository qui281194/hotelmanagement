/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.BookingDto;
import fpt.aptech.hotelapi.dto.VoucherDto;
import fpt.aptech.hotelapi.models.Voucher;
import fpt.aptech.hotelapi.repository.VoucherRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class VoucherService {

    final private VoucherRepository _vouRepo;

    @Autowired
    public VoucherService(VoucherRepository _vouRepo) {
        this._vouRepo = _vouRepo;
    }

    public double calculateDiscount(BookingDto bookingDto, VoucherDto voucherDto) {
        double totalprice = bookingDto.getTotal_price();
        if (voucherDto.isActive() == true) {
            if (voucherDto != null) {
                double cashAmountDiscount = voucherDto.getCashAmountDiscount();
                double percentDiscount = voucherDto.getPercentageDiscount();
                if (cashAmountDiscount > 0) {
                    return Math.min(totalprice, cashAmountDiscount);
                } else if (percentDiscount > 0) {
                    return totalprice * (percentDiscount / 100);
                }
            }
        }

        return 0.0;
    }

    private Voucher maptoModel(VoucherDto voucherdto) {
        Voucher voucher = new Voucher();
        voucher.setId(voucherdto.getId());
        voucher.setCode(voucherdto.getCode());
        voucher.setType(voucherdto.getType());
        voucher.setTitle(voucherdto.getTitle());
        voucher.setCashAmountDiscount(voucherdto.getCashAmountDiscount());
        voucher.setPercentageDiscount(voucherdto.getPercentageDiscount());
        voucher.setExpirationDate(voucherdto.getExpirationDate());
        voucher.setActive(voucherdto.isActive());
        return voucher;
    }

    private VoucherDto maptoDto(Voucher voucher) {
        VoucherDto voucherdto = new VoucherDto();
        voucherdto.setId(voucher.getId());
        voucherdto.setCode(voucher.getCode());
        voucherdto.setType(voucher.getType());
        voucherdto.setTitle(voucher.getTitle());
        voucherdto.setCashAmountDiscount(voucher.getCashAmountDiscount() != null ? voucher.getCashAmountDiscount() : 0.0);

        // Check if percentageDiscount is not null before invoking doubleValue()
        voucherdto.setPercentageDiscount(voucher.getPercentageDiscount() != null ? voucher.getPercentageDiscount().doubleValue() : 0.0);

        voucherdto.setExpirationDate(voucher.getExpirationDate());
        voucherdto.setActive(voucher.isActive());
        return voucherdto;
    }

    public List<VoucherDto> allVoucher() {
        return _vouRepo.findAll().stream()
                .map(mapper -> maptoDto(mapper))
                .collect(Collectors.toList());
    }

    public VoucherDto createNewVoucher(VoucherDto newVoucherDto) {
        Voucher newVoucher = maptoModel(newVoucherDto);
        Voucher savedVouver = _vouRepo.save(newVoucher);
        return maptoDto(savedVouver);
    }

    public VoucherDto editVoucher(int id, VoucherDto voucherDto) {
        Optional<Voucher> existingVoucherOptional = _vouRepo.findById(voucherDto.getId());
        if (existingVoucherOptional.isPresent()) {
            Voucher existingVoucher = existingVoucherOptional.get();
            existingVoucher.setCode(voucherDto.getCode());
            existingVoucher.setType(voucherDto.getType());
            existingVoucher.setTitle(voucherDto.getTitle());
            existingVoucher.setCashAmountDiscount(voucherDto.getCashAmountDiscount());
            existingVoucher.setPercentageDiscount(voucherDto.getPercentageDiscount());
            existingVoucher.setExpirationDate(voucherDto.getExpirationDate());
            existingVoucher.setActive(voucherDto.isActive());
            Voucher updatedVoucher = _vouRepo.save(existingVoucher);
            return maptoDto(updatedVoucher);
        } else {
            throw new NoSuchElementException("Voucher not found");
        }
    }

    public VoucherDto disableVoucher(int voucherId) {
        Optional<Voucher> existingVoucherOptional = _vouRepo.findById(voucherId);

        if (existingVoucherOptional.isPresent()) {
            Voucher existingVoucher = existingVoucherOptional.get();
            existingVoucher.setActive(!existingVoucher.isActive());
            Voucher updatedVoucher = _vouRepo.save(existingVoucher);
            return maptoDto(updatedVoucher);
        } else {
            throw new NoSuchElementException("Voucher not found");
        }
    }

    public VoucherDto findVoucher(int id) {
        Optional<Voucher> voucherOptional = _vouRepo.findById(id);
        return voucherOptional.map(this::maptoDto).orElse(null);

    }

    public List<VoucherDto> findByTitle(String title) {
        List<Voucher> vouchers = _vouRepo.findByTitleContainingIgnoreCase(title);
        return vouchers.stream().map(this::maptoDto).collect(Collectors.toList());
    }

    public void deleteVoucher(int id) throws Exception {
        Optional<Voucher> voucherOptional = _vouRepo.findById(id);

        if (voucherOptional.isPresent()) {
            // Voucher found, delete it
            _vouRepo.deleteById(id);
        } else {

            throw new Exception("Not found");
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpirationVouchers() throws Exception {
        List<VoucherDto> expirationVouchers = allVoucher().stream().filter(voucherdto -> voucherdto
                .getExpirationDate()
                .isBefore(LocalDate.now()) && voucherdto.isActive())
                .collect(Collectors.toList());

        for (VoucherDto exVoucherDto : expirationVouchers) {
            deleteVoucher(exVoucherDto.getId());
        }
    }

}
