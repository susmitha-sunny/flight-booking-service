package com.gotravel.flightbookingservice.repository;

import com.gotravel.flightbookingservice.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Query(value = "SELECT p.seat_number FROM passenger p WHERE p.flight_id = ?1 and p.departure_date = ?2 and p.departure_time = ?3 ", nativeQuery = true)
    List<String> findReservedSeat();

    @Query(value = "SELECT p.seat_number FROM passenger p WHERE p.return_flight_id = ?1 and p.return_date = ?2 and p.return_departure_time = ?3 ", nativeQuery = true)
    List<String> findReturnReservedSeat();
}
