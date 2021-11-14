package com.gotravel.flightbookingservice.controller;


import com.gotravel.flightbookingservice.exception.ValueNotFoundException;
import com.gotravel.flightbookingservice.model.CouponRequest;
import com.gotravel.flightbookingservice.model.CouponResponse;
import com.gotravel.flightbookingservice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping(value = "/flight-booking-service/coupon")
    public CouponResponse execute(@RequestBody final CouponRequest couponRequest) throws ValueNotFoundException {
        return couponService.getCouponResponse(couponRequest);
    }
}
