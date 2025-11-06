package com.example.demo.repository;

import com.example.demo.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    // Find all shows for a given movie and date
    List<Show> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);

    // Find all shows for a theatre and date
    List<Show> findByTheatreIdAndShowDate(Long theatreId, LocalDate showDate);
    
    Optional<Show> findById(Long showId);


    // You can add more custom queries as needed
}
