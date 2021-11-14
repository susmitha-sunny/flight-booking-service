package com.gotravel.flightbookingservice.service;

import com.gotravel.flightbookingservice.entity.Passenger;
import com.gotravel.flightbookingservice.entity.Reservation;
import com.gotravel.flightbookingservice.exception.InsertionFailedException;
import com.gotravel.flightbookingservice.exception.InvalidRequestException;
import com.gotravel.flightbookingservice.model.*;
import com.gotravel.flightbookingservice.repository.PassengerRepository;
import com.gotravel.flightbookingservice.repository.ReservationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public BookingResponse getBookingResponse(final BookingRequest bookingRequest) throws InsertionFailedException, InvalidRequestException {
        preValidate(bookingRequest);

        Reservation reservation = reservationRepository.save(buildReservation(bookingRequest));
        if (Objects.nonNull(reservation)) {
            List<Passenger> passengerList = buildPassengerList(bookingRequest, reservation.getPnr());

            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setReservation(reservation);
            bookingResponse.setPassengersList(passengerList);
            return bookingResponse;
        }
        throw new InsertionFailedException("Unable to create booking.");
    }

    private void preValidate(final BookingRequest bookingRequest) throws InvalidRequestException {
        if (StringUtils.isBlank(bookingRequest.getDepartureAirport())) {
            throw new InvalidRequestException("Missing Departure Airport");
        }
        if (StringUtils.isBlank(bookingRequest.getArrivalAirport())) {
            throw new InvalidRequestException("Missing Arrival Airport");
        }
        if (Objects.isNull(bookingRequest.getEmail())) {
            throw new InvalidRequestException("Missing Email ID");
        }
        if (Objects.isNull(bookingRequest.getTripType())) {
            throw new InvalidRequestException("Missing Trip Type");
        }
        if (Objects.isNull(bookingRequest.getFlightDetails())) {
            throw new InvalidRequestException("Missing Return Flight Details");
        }
        if (bookingRequest.getTripType().equals(TripType.RT) && Objects.isNull(bookingRequest.getReturnFlightDetails())) {
            throw new InvalidRequestException("Missing Return Flight Details");
        }
    }

    private List<Passenger> buildPassengerList(final BookingRequest bookingRequest, final String pnr) {
        final AtomicInteger atomicInteger = new AtomicInteger(1101);

        //list for inserting into DB
        List<Passenger> passengerList = Optional.ofNullable(bookingRequest.getPassengerDetailsList())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(passengerDetails -> buildPassenger(passengerDetails, pnr, atomicInteger))
                .collect(Collectors.toList());

        //List after successful insertion
        return Optional.ofNullable(passengerList)
                .orElse(Collections.emptyList())
                .stream()
                .map(passenger -> passengerRepository.save(passenger))
                .collect(Collectors.toList());
    }

    //map passenger details from request to passenger entity
    private Passenger buildPassenger(final PassengerDetails passengerDetails,  final String pnr, final AtomicInteger atomicInteger) {
        Passenger pax = new Passenger();
        pax.setFirstName(passengerDetails.getFirstName());
        pax.setMiddleName(passengerDetails.getMiddleName());
        pax.setLastName(passengerDetails.getLastName());
        pax.setDob(passengerDetails.getDateOfBirth());
        pax.setGender(passengerDetails.getGender());
        pax.setPassengerType(passengerDetails.getType());
        pax.setSeatNumber(passengerDetails.getSeatNumber());
        pax.setReturnSeatNumber(passengerDetails.getReturnSeatNumber());
        pax.setMealPreference(passengerDetails.getMealPreference());
        pax.setPnr(pnr);
        pax.setTicketNumber(generateTicketNumber(pnr, atomicInteger.getAndIncrement()));
        return pax;
    }

    private String generateTicketNumber(final String pnr, final int index) {
        return pnr.concat(String.valueOf(index));
    }

    //map request to reservation
    private Reservation buildReservation(final BookingRequest bookingRequest) {
        Reservation reservation = new Reservation();
        reservation.setBookingStatus(BookingStatusType.ACTIVE);
        reservation.setPnr(generateUniquePnr());
        reservation.setEmailId(bookingRequest.getEmail());
        reservation.setPhone(bookingRequest.getPhone());
        reservation.setDepartureAirport(bookingRequest.getDepartureAirport());
        reservation.setArrivalAirport(bookingRequest.getArrivalAirport());
        reservation.setTotalFare(bookingRequest.getTotalFare());
        reservation.setTripType(bookingRequest.getTripType());
        reservation.setAdultCount(bookingRequest.getAdultCount());
        reservation.setChildCount(bookingRequest.getChildCount());
        reservation.setInfantCount(bookingRequest.getInfantCount());

        setFlightDetails(reservation, bookingRequest.getFlightDetails());
        if (reservation.getTripType().equals(TripType.RT)) {
            setReturnFlightDetails(reservation, bookingRequest.getReturnFlightDetails());
        }
        return reservation;
    }

    private void setFlightDetails(final Reservation reservation, final FlightDetails flightDetails) {
        reservation.setFlightId(flightDetails.getFlightId());
        reservation.setFlightNumber(flightDetails.getFlightNumber());
        reservation.setAirlineName(flightDetails.getAirlineName());
        reservation.setIataCode(flightDetails.getIataCode());
        reservation.setDepartureTime(flightDetails.getDepartureTime());
        reservation.setArrivalTime(flightDetails.getArrivalTime());
        reservation.setDuration(flightDetails.getDuration());
        reservation.setDepartureDate(flightDetails.getDepartureDate());
    }

    private void setReturnFlightDetails(final Reservation reservation, final FlightDetails returnFlightDetails) {
        reservation.setReturnFlightId(returnFlightDetails.getFlightId());
        reservation.setReturnFlightNumber(returnFlightDetails.getFlightNumber());
        reservation.setReturnAirlineName(returnFlightDetails.getAirlineName());
        reservation.setReturnIataCode(returnFlightDetails.getIataCode());
        reservation.setReturnDepartureTime(returnFlightDetails.getDepartureTime());
        reservation.setReturnArrivalTime(returnFlightDetails.getArrivalTime());
        reservation.setReturnDuration(returnFlightDetails.getDuration());
        reservation.setReturnDate(returnFlightDetails.getDepartureDate());
    }

    private String generateUniquePnr() {
        List<String> pnrList = reservationRepository.findAllPnr();
        String pnr = generatePnr();
        while (pnrList.contains(pnr)) {
            pnr = generatePnr();
        }
        return pnr;
    }

    private String generatePnr() {
        String randomUuid1 = getRandomUUID();
        String randomUuid2 = getRandomUUID();
        return randomUuid1.substring(0, 3).concat(randomUuid2.substring(0, 3)).toUpperCase();
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("[^A-Za-z]", "");
    }

}
