/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import ch.qos.logback.core.model.Model;
import fpt.aptech.hotelapi.dto.VoucherDto;
import fpt.aptech.hotelapi.service.VoucherService;
import java.util.List;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/vouchercontroller")
public class VoucherAPIController {
    private VoucherService _voucherService;
    
    @Autowired
    public VoucherAPIController(VoucherService _vouService){
        this._voucherService=_vouService;
    }
    
    @GetMapping("/all")
    public List<VoucherDto> function_allVoucher(){
        return _voucherService.allVoucher();
    }
    @GetMapping("/one/{id}")
    public VoucherDto function_findOne(@PathVariable int id){
        return _voucherService.findVoucher(id);
    }
     @GetMapping("/search")
    public ResponseEntity<List<VoucherDto>> searchByTitle(@RequestParam String title) {
        List<VoucherDto> vouchers = _voucherService.findByTitle(title);
        return new ResponseEntity<>(vouchers, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public VoucherDto function_createNewVoucher(@RequestBody VoucherDto newVoucherdto){
        return _voucherService.createNewVoucher(newVoucherdto);
    }
    @DeleteMapping("/delete/{id}")
    public void function_deleteVoucher(@PathVariable int id) throws Exception {
        _voucherService.deleteVoucher(id);
    }
    
}
