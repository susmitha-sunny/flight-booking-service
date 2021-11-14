package com.gotravel.flightbookingservice.model;

import com.gotravel.flightbookingservice.entity.Passenger;
import com.gotravel.flightbookingservice.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookingResponse {
    private Reservation reservation;
    private List<Passenger> passengersList;
}
