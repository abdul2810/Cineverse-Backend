package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SeatBooking;

import jakarta.transaction.Transactional;

@Repository
public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {

    // Find all reserved seatNumbers for a show
    @Query("SELECT sb.seatNumber FROM SeatBooking sb WHERE sb.showId = :showId")
    List<String> findSeatNumberByShowId(@Param("showId") Long showId);

    // Find reserved seat numbers for a show in given seat number list
    @Query("SELECT sb.seatNumber FROM SeatBooking sb WHERE sb.showId = :showId AND sb.seatNumber IN :seatNumbers")
    List<String> findSeatNumberByShowIdAndSeatNumberIn(@Param("showId") Long showId, @Param("seatNumbers") List<String> seatNumbers);

    // Delete seat bookings by showId and list of seat numbers
    @Transactional
    @Modifying
    void deleteByShowIdAndSeatNumberIn(Long showId, List<String> seatNumbers);

    // Delete all seat bookings associated with a ticket booking id
    @Transactional
    @Modifying
    void deleteByTicket_Bookingid(Long bookingId);
}
