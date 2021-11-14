package com.gotravel.flightbookingservice.entity;


import com.gotravel.flightbookingservice.model.BookingStatusType;
import com.gotravel.flightbookingservice.model.TripType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    private String pnr;

    @Column(name = "email_id")
    private String emailId;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatusType bookingStatus;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type")
    private TripType tripType;

    @Column(name = "total_fare")
    private BigDecimal totalFare;

    @Column(name = "adult_count")
    private int adultCount;

    @Column(name = "child_count")
    private int childCount;

    @Column(name = "infant_count")
    private int infantCount;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "flight_id")
    private int flightId;

    @Column(name = "return_flight_id")
    private int returnFlightId;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "return_flight_number")
    private String returnFlightNumber;

    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "return_airline_name")
    private String returnAirlineName;

    @Column(name = "iata_code")
    private String iataCode;

    @Column(name = "return_iata_code")
    private String returnIataCode;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "return_departure_time")
    private LocalTime returnDepartureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "return_arrival_time")
    private LocalTime returnArrivalTime;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name = "return_duration")
    private LocalTime returnDuration;
}
