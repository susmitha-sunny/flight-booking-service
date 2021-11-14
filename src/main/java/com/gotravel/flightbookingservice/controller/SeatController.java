package com.gotravel.flightbookingservice.controller;


import com.gotravel.flightbookingservice.model.ReservedSeatRequest;
import com.gotravel.flightbookingservice.model.ReservedSeatResponse;
import com.gotravel.flightbookingservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping(value = "/flight-booking-service/reservedseat")
    public ReservedSeatResponse execute(@RequestBody final ReservedSeatRequest reservedSeatRequest) {
        return seatService.getReservedSeats(reservedSeatRequest);
    }
}
