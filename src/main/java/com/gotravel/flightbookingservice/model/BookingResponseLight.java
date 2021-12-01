package com.gotravel.flightbookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookingResponseLight {
    private String pnr;
    private List<String> tickets;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
