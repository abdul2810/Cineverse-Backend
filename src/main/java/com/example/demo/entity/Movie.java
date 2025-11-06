package com.example.demo.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int movieid;

    @Column(nullable = false)
    private String moviename;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private LocalDate relesedate;

    private String poster; // Poster image URL

    private String duration; // e.g. "2h 30m"

    @Column(length = 2000) // longer text allowed
    private String synopsis;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String cast;  
    // stored as JSON string: 
    // [{ "image": "...", "realName": "Actor", "character": "Hero" }, ...]

    @Lob
    @Column(columnDefinition = "TEXT")
    private String crew;  
    // stored as JSON string: 
    // [{ "image": "...", "role": "Director", "name": "Person" }, ...]

    // ✅ Default constructor
    public Movie() {}

    // ✅ Constructor with fields
    public Movie(int movieid, String moviename, String director, String language,
                 LocalDate relesedate, String poster, String duration, String synopsis,
                 String cast, String crew) {
        this.movieid = movieid;
        this.moviename = moviename;
        this.director = director;
        this.language = language;
        this.relesedate = relesedate;
        this.poster = poster;
        this.duration = duration;
        this.synopsis = synopsis;
        this.cast = cast;
        this.crew = crew;
    }

    // ✅ Getters & Setters
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getRelesedate() {
        return relesedate;
    }

    public void setRelesedate(LocalDate relesedate) {
        this.relesedate = relesedate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieid=" + movieid +
                ", moviename='" + moviename + '\'' +
                ", director='" + director + '\'' +
                ", language='" + language + '\'' +
                ", relesedate=" + relesedate +
                ", poster='" + poster + '\'' +
                ", duration='" + duration + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", cast='" + cast + '\'' +
                ", crew='" + crew + '\'' +
                '}';
    }
}
