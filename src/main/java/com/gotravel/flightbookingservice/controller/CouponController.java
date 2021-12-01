package com.gotravel.flightbookingservice.controller;


import com.gotravel.flightbookingservice.entity.Coupon;
import com.gotravel.flightbookingservice.exception.ValueNotFoundException;
import com.gotravel.flightbookingservice.model.CouponRequest;
import com.gotravel.flightbookingservice.model.CouponResponse;
import com.gotravel.flightbookingservice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

//    @GetMapping(value = "/coupon")
//    public CouponResponse execute(@RequestBody final CouponRequest couponRequest) throws ValueNotFoundException {
//        return couponService.getCouponResponse(couponRequest);
//    }

    @CrossOrigin
    @GetMapping(value = "/coupon")
    public CouponResponse execute(@RequestParam(value = "code") final String code,
                                  @RequestParam(value = "totalFare") final int totalFare) throws ValueNotFoundException {
        System.out.println("Inside coupon controller");
        CouponRequest request = new CouponRequest(code, BigDecimal.valueOf(totalFare));
        return couponService.getCouponResponse(request);
    }

    @PostMapping(value = "/createcoupon")
    public Coupon execute(@RequestBody final Coupon coupon) {
        return couponService.createCoupon(coupon);
    }
}
