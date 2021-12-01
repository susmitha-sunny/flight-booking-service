package com.gotravel.flightbookingservice.controller;


import com.gotravel.flightbookingservice.exception.InsertionFailedException;
import com.gotravel.flightbookingservice.exception.InvalidRequestException;
import com.gotravel.flightbookingservice.model.*;
import com.gotravel.flightbookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @CrossOrigin
    @PostMapping(value = "/booking")
    public BookingResponse execute(@RequestBody final BookingRequest bookingRequest)
            throws InsertionFailedException, InvalidRequestException {
        System.out.println("Inside booking controller");
        return bookingService.getBookingResponse(bookingRequest);
    }

    @PostMapping(value = "/bookingLight")
    public BookingResponseLight executeLight(@RequestBody final BookingRequest bookingRequest)
            throws InsertionFailedException, InvalidRequestException {
        int diff = (bookingRequest.getAdultCount() + bookingRequest.getChildCount() + bookingRequest.getInfantCount())
                - bookingRequest.getPassengerDetailsList().size();
        int i;
        if (diff != 0) {
            for (i = 0; i < bookingRequest.getAdultCount() - 1; i++) {
                bookingRequest.getPassengerDetailsList().add(getPax(PassengerType.ADT, i));
            }
            for (i = 0; i < bookingRequest.getChildCount(); i++) {
                bookingRequest.getPassengerDetailsList().add(getPax(PassengerType.CHD, i));
            }
            for (i = 0; i < bookingRequest.getInfantCount(); i++) {
                bookingRequest.getPassengerDetailsList().add(getPax(PassengerType.INF, i));
            }
        }
        return bookingService.getBookingResponseLight(bookingRequest);
    }

    private PassengerDetails getPax(final PassengerType type, final int i) {
       PassengerDetails pax =  new PassengerDetails();
       pax.setFirstName("passenger" + i);
       pax.setLastName("last" + i);
       pax.setGender(GenderType.M);
       pax.setDateOfBirth(LocalDate.now());
       pax.setType(type);
       return pax;
    }
}
