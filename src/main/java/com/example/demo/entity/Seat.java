package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;   // e.g. "A1", "B3" (can be generated from row + seatNo)

    @Column(name = "`row`")
    private String row;          // e.g. "A", "B", "C"
    
    private int seatNo;          // e.g. 1, 2, 3
    
    // Remove reserved here, because booking is per show (tracked separately)
    // You can remove this field in Seat if you track reservations in a booking table

    public Seat() {}

    public Seat(Long id, String seatNumber, String row, int seatNo) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.row = row;
        this.seatNo = seatNo;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getRow() { return row; }
    public void setRow(String row) { this.row = row; }

    public int getSeatNo() { return seatNo; }
    public void setSeatNo(int seatNo) { this.seatNo = seatNo; }
}
