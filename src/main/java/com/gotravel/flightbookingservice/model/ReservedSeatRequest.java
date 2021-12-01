package com.gotravel.flightbookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservedSeatRequest {
    private int flightId;
    private LocalDate departureDate;
    private LocalTime departureTime;
}
