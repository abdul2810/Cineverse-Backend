package com.example.demo.dto;

import java.util.List;

public class CreateShowRequest {
    private Long movieId;
    private String showDate;
    private List<TheatreShow> theatres;

    // Getters and setters

    public static class TheatreShow {
        private Long theatreId;
        private List<String> showtimes;

        public Long getTheatreId() {
            return theatreId;
        }

        public void setTheatreId(Long theatreId) {
            this.theatreId = theatreId;
        }

        public List<String> getShowtimes() {
            return showtimes;
        }

        public void setShowtimes(List<String> showtimes) {
            this.showtimes = showtimes;
        }
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public List<TheatreShow> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<TheatreShow> theatres) {
        this.theatres = theatres;
    }
}
