package com.gotravel.flightbookingservice.controller;


import com.gotravel.flightbookingservice.exception.InsertionFailedException;
import com.gotravel.flightbookingservice.exception.InvalidRequestException;
import com.gotravel.flightbookingservice.model.BookingRequest;
import com.gotravel.flightbookingservice.model.BookingResponse;
import com.gotravel.flightbookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/booking")
    public BookingResponse execute(@RequestBody final BookingRequest bookingRequest)
            throws InsertionFailedException, InvalidRequestException {
        return bookingService.getBookingResponse(bookingRequest);
    }
}
