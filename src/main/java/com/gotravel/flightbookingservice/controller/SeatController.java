package com.gotravel.flightbookingservice.controller;


import com.gotravel.flightbookingservice.model.ReservedSeatRequest;
import com.gotravel.flightbookingservice.model.ReservedSeatResponse;
import com.gotravel.flightbookingservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class SeatController {

    @Autowired
    private SeatService seatService;

    @CrossOrigin
    @GetMapping(value = "/reservedseat")
    public ReservedSeatResponse execute(@RequestParam(value = "flightId") final int flightId,
                                        @RequestParam(value = "departureDate") final String departureDate,
                                        @RequestParam(value = "departureTime") final String departureTime
                                        ) {
        String newDepartureTime = departureTime.substring(1, departureTime.length() - 1);
        ReservedSeatRequest request = new ReservedSeatRequest();
        request.setFlightId(flightId);
        if (departureDate == null || departureDate.equals("null")) {
            request.setDepartureDate(null);
        } else {
            request.setDepartureDate(LocalDate.parse(departureDate));
        }
        if (newDepartureTime == null || newDepartureTime.equals("null")) {
            request.setDepartureTime(null);
        } else {
            request.setDepartureTime(LocalTime.parse(newDepartureTime));
        }
        return seatService.getReservedSeats(request);
    }
}
