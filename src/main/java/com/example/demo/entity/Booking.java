package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long showId;

    private Long seatId;

    private boolean reserved;
    
    public Booking() {}

    public Booking(Long id, Long showId, Long seatId, boolean reserved) {
        this.id = id;
        this.showId = showId;
        this.seatId = seatId;
        this.reserved = reserved;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }

    public Long getSeatId() { return seatId; }
    public void setSeatId(Long seatId) { this.seatId = seatId; }

    public boolean isReserved() { return reserved; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }
}
