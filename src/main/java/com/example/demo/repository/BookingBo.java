package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Ticket;

public interface BookingBo extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByMobilenumber(Long mobilenumber);
    List<Ticket> findByUseremail(String useremail);
}
