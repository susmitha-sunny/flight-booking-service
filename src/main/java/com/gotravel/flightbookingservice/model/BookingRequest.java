package com.gotravel.flightbookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookingRequest {
    private BigDecimal totalFare;
    private String departureAirport;
    private String arrivalAirport;
    private TripType tripType;
    private int adultCount;
    private int childCount;
    private int infantCount;
    private String email;
    private String phone;
    private FlightDetails flightDetails;
    private FlightDetails returnFlightDetails;
    private List<PassengerDetails> passengerDetailsList;
}
