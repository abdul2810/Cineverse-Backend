package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seat_booking")
public class SeatBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "show_id", nullable = false)
    private Long showId;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;
    
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


    // Additional fields like userId, booking timestamp, etc. if needed

    public SeatBooking() {}

    public SeatBooking(Long showId, String seatNumber) {
        this.showId = showId;
        this.seatNumber = seatNumber;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
