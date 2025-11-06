package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingid;

    private int movieid;
    private String moviename;
    private String cusname;
    private long mobilenumber;
    private int numberofticket;
    private LocalDate bookdate;
    private LocalDate showdate;
    private String showtime;
    private String theatre;
    private String seatnumbers;
    private String useremail;

    // ✅ Add this line
    private int showId;

//    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SeatBooking> seatBookings;

    // No-args constructor
    public Ticket() {}

    // All-args constructor
    public Ticket(int bookingid, int movieid, String moviename,
                  String cusname, long mobilenumber, int numberofticket,
                  LocalDate bookdate, LocalDate showdate,
                  String showtime, String theatre, String seatnumbers,
                  String useremail, int showId) {

        this.bookingid = bookingid;
        this.movieid = movieid;
        this.moviename = moviename;
        this.cusname = cusname;
        this.mobilenumber = mobilenumber;
        this.numberofticket = numberofticket;
        this.bookdate = bookdate;
        this.showdate = showdate;
        this.showtime = showtime;
        this.theatre = theatre;
        this.seatnumbers = seatnumbers;
        this.useremail = useremail;
        this.showId = showId;
    }

    // Getters and setters for all fields (including showId)

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

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

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public long getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public int getNumberofticket() {
        return numberofticket;
    }

    public void setNumberofticket(int numberofticket) {
        this.numberofticket = numberofticket;
    }

    public LocalDate getBookdate() {
        return bookdate;
    }

    public void setBookdate(LocalDate bookdate) {
        this.bookdate = bookdate;
    }

    public LocalDate getShowdate() {
        return showdate;
    }

    public void setShowdate(LocalDate showdate) {
        this.showdate = showdate;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getTheatre() {
        return theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public String getSeatnumbers() {
        return seatnumbers;
    }

    public void setSeatnumbers(String seatnumbers) {
        this.seatnumbers = seatnumbers;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    // ✅ Getter and Setter for showId
    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }
}
