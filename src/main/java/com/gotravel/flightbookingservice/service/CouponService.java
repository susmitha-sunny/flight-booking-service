package com.gotravel.flightbookingservice.service;

import com.gotravel.flightbookingservice.entity.Coupon;
import com.gotravel.flightbookingservice.exception.ValueNotFoundException;
import com.gotravel.flightbookingservice.model.CouponRequest;
import com.gotravel.flightbookingservice.model.CouponResponse;
import com.gotravel.flightbookingservice.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    private static final String TOPIC = "flight_discountcoupon";

    private static final Logger LOG = LoggerFactory.getLogger(CouponService.class);

    public Coupon createCoupon(final Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public CouponResponse getCouponResponse(final CouponRequest couponRequest) throws ValueNotFoundException {
        Optional<Coupon> coupon = couponRepository.findByCode(couponRequest.getCode());
        if (coupon.isPresent()) {
            return buildCouponResponse(coupon.get(), couponRequest);
        }
        throw new ValueNotFoundException("No matching coupon found");
    }

    private CouponResponse buildCouponResponse(final Coupon coupon, final CouponRequest couponRequest) {
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setCoupon(coupon);
        couponResponse.setTotalFare(couponRequest.getTotalFare());
        couponResponse.setDiscountedTotalFare(applyDiscount(couponRequest.getTotalFare(), coupon.getDiscountPercent()));
        return couponResponse;
    }

    private BigDecimal applyDiscount(final BigDecimal totalFare, final BigDecimal discount) {
        BigDecimal discountFare = (totalFare.multiply(discount)).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
        return totalFare.subtract(discountFare);
    }

//    @KafkaListener(topics = TOPIC, groupId = "group_id", containerFactory = "userKafkaListenerFactory")
//    public void createCouponFromQueue(final Coupon coupon) {
//        LOG.info("Consumed coupon from queue successfully {}", coupon);
//        couponRepository.save(coupon);
//    }
}
