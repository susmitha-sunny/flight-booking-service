package com.gotravel.flightbookingservice.repository;

import com.gotravel.flightbookingservice.entity.Coupon;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Cacheable(value = "couponCache", key = "#code")
    Optional<Coupon> findByCode(String code);

}
