package com.gotravel.flightbookingservice.model;

import com.gotravel.flightbookingservice.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CouponResponse {
    private Coupon coupon;
    private BigDecimal totalFare;
    private BigDecimal discountedTotalFare;
}
