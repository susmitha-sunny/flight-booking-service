package com.gotravel.flightbookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservedSeatRequest {
    private FlightDetails flightDetails;
    private FlightDetails returnFlightDetails;
    private TripType tripType;
}
