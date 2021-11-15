package com.gotravel.flightbookingservice.service;

import com.gotravel.flightbookingservice.model.ReservedSeatRequest;
import com.gotravel.flightbookingservice.model.ReservedSeatResponse;
import com.gotravel.flightbookingservice.model.TripType;
import com.gotravel.flightbookingservice.repository.PassengerRepository;
import com.gotravel.flightbookingservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public ReservedSeatResponse getReservedSeats(final ReservedSeatRequest reservedSeatRequest) {
        List<Integer> seats = Arrays.asList(28, 31);
        List<Integer> returnSeats = new ArrayList<>();

        if (reservedSeatRequest.getTripType().equals(TripType.RT)) {
            returnSeats = Arrays.asList(14);
        }

        //Implement seat retrieval logic
        /*FlightDetails flightDetails = reservedSeatRequest.getFlightDetails();
        List<String> pnrList = reservationRepository.findPnr(flightDetails.getFlightId(),
                flightDetails.getDepartureDate(), flightDetails.getDepartureTime());
        List<Integer> seats = getSeats(pnrList);
        if(reservedSeatRequest.getTripType().equals(TripType.RT)) {
            FlightDetails returnFlightDetails = reservedSeatRequest.getReturnFlightDetails();
            List<String> returnPnrList = reservationRepository.findReturnPnr(returnFlightDetails.getFlightId(),
                    returnFlightDetails.getDepartureDate(), returnFlightDetails.getDepartureTime());
            returnSeats = getReturnSeats(returnPnrList);
        }*/

        ReservedSeatResponse reservedSeatResponse = new ReservedSeatResponse();
        reservedSeatResponse.setSeatList(seats);
        reservedSeatResponse.setReturnSeatList(returnSeats);
        return reservedSeatResponse;
    }


}
