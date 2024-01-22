/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.dto.BookingDto;
import fpt.aptech.hotelapi.dto.PaymentDto;
import fpt.aptech.hotelapi.service.PaymentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author TuanNguyen
 */
@RestController
@RequestMapping("/api/paymentcontroller")
public class PaymentAPIController {
    private PaymentService _paymentService;

    @Autowired
    public PaymentAPIController(PaymentService _paymentService) {
        this._paymentService = _paymentService;
    }
    
    @GetMapping("/showbookinginfo/{bookingId}")
    public BookingDto function_showBookingInfoToPayment(@PathVariable("bookingId") int bookingId) {
        return _paymentService.showBookingInfoToPayment(bookingId);
    }
    
    @GetMapping("/all")
    public List<PaymentDto> function_allPayment() {
        return _paymentService.allPayment();
    }
    
    @GetMapping("/allpaymentbycustomer/{customerId}")
    public List<PaymentDto> function_allPaymentByCustomer(@PathVariable("customerId") int customerId) {
        return _paymentService.allPaymentByCustomer(customerId);
    }    
    
    @PostMapping("/create")
    public PaymentDto function_createNewPayment(@RequestBody PaymentDto newPaymentDto) {
        return _paymentService.createNewPayment(newPaymentDto);
    }
    
    @GetMapping("/confirm/{paymentId}")
    public PaymentDto function_confirmPayment(@PathVariable("paymentId") int paymentId) {
        return _paymentService.confirmPayment(paymentId);
    }
}
