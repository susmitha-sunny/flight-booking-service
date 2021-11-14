package com.gotravel.flightbookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PassengerDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private GenderType gender;
    private PassengerType type;
    private LocalDate dateOfBirth;
    private int seatNumber;
    private int returnSeatNumber;
    private MealType mealPreference;
}
