package com.example.demo.repository;

import com.example.demo.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Get all master seat layout ordered by row and seatNo for UI layout
    List<Seat> findAllByOrderByRowAscSeatNoAsc();

    // Optional: find seat by seatNumber if needed
    Seat findBySeatNumber(String seatNumber);
}
