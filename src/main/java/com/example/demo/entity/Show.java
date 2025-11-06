package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long movieId;     // FK to Movie
    private Long theatreId;   // FK to Theatre

    private LocalDate showDate;  // e.g. 2025-07-25
    private String showTime;     // e.g. "19:30"

    public Show() {}

    public Show(Long id, Long movieId, Long theatreId, LocalDate showDate, String showTime) {
        this.id = id;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.showDate = showDate;
        this.showTime = showTime;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
