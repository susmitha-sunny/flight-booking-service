package com.gotravel.flightbookingservice.repository;

import com.gotravel.flightbookingservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT r.pnr FROM reservation r", nativeQuery = true)
    List<String> findAllPnr();

    @Query(value = "SELECT r.pnr FROM reservation r WHERE r.flight_id = ?1 and r.departure_date = ?2 and r.departure_time = ?3 and r.booking_status = 'ACTIVE' ", nativeQuery = true)
    List<String> findPnr(int flightId, LocalDate departureDate, LocalTime departureTime);

    @Query(value = "SELECT r.pnr FROM reservation r WHERE r.return_flight_id = ?1 and r.return_date = ?2 and r.return_departure_time = ?3 ", nativeQuery = true)
    List<String> findReturnPnr(int flightId, LocalDate departureDate, LocalTime departureTime);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation r SET r.booking_status = 'CANCELLED' WHERE r.pnr = ?1", nativeQuery = true)
    void cancelReservation(String pnr);

    @Query(value = "SELECT r.pnr FROM reservation r WHERE r.airline_name= ?1 ", nativeQuery = true)
    List<String> findPnrByAirlineName(String airlineName);

}
