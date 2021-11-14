package com.gotravel.flightbookingservice.service;

import com.gotravel.flightbookingservice.entity.Coupon;
import com.gotravel.flightbookingservice.exception.ValueNotFoundException;
import com.gotravel.flightbookingservice.model.CouponRequest;
import com.gotravel.flightbookingservice.model.CouponResponse;
import com.gotravel.flightbookingservice.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public CouponResponse getCouponResponse(final CouponRequest couponRequest) throws ValueNotFoundException{
        Optional<Coupon> coupon = couponRepository.findByCode(couponRequest.getCode());
        if(coupon.isPresent()) {
            return buildCouponResponse(coupon.get(),couponRequest);
        }
        throw new ValueNotFoundException("No matching coupon found");
    }

    private CouponResponse buildCouponResponse(Coupon coupon, CouponRequest couponRequest) {
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setCoupon(coupon);
        couponResponse.setTotalFare(couponRequest.getTotalFare());
        couponResponse.setDiscountedTotalFare(applyDiscount(couponRequest.getTotalFare(), coupon.getDiscountPercent()));
        return couponResponse;
    }

    private BigDecimal applyDiscount(BigDecimal totalFare, BigDecimal discount) {
        return totalFare.subtract(totalFare.multiply(discount));
    }
}
