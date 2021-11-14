package com.gotravel.flightbookingservice.entity;


import com.gotravel.flightbookingservice.model.GenderType;
import com.gotravel.flightbookingservice.model.MealType;
import com.gotravel.flightbookingservice.model.PassengerType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private int passengerId;

    @Column(name = "ticket_number")
    private String ticketNumber;

    private String pnr;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "passenger_type")
    private PassengerType passengerType;

    private LocalDate dob;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "return_seat_number")
    private int returnSeatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_preference")
    private MealType mealPreference;
}
