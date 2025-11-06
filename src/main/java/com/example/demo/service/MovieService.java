package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SeatBooking;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Movie;
import com.example.demo.repository.AdminBo;
import com.example.demo.repository.BookingBo;
import com.example.demo.repository.MovieBo;
import com.example.demo.repository.SeatBookingRepository;

@Service
public class MovieService {

    @Autowired
    private MovieBo mb;

    @Autowired
    private BookingBo bo;

    @Autowired
    private AdminBo ab;

    @Autowired
    private SeatBookingRepository seatBookingRepo;  // Repository for seat bookings

    // Insert a new movie
    public Movie insertMovie(Movie m) {
        return mb.save(m);
    }

    // Update an existing movie
    public Movie updateMovie(Movie m) {
        return mb.save(m);
    }

    /**
     * Book movie tickets and save individual seat bookings for the show.
     */
    @Transactional
    public Ticket bookmovie(Ticket ticket) {
        // Save Ticket entity first
        Ticket savedTicket = bo.save(ticket);

        // Save reserved seats individually to seat_booking table
        if (ticket.getSeatnumbers() != null && !ticket.getSeatnumbers().isEmpty()) {
            // Split seat numbers string and trim whitespace
            List<String> seatList = Arrays.asList(ticket.getSeatnumbers().split("\\s*,\\s*"));

            List<SeatBooking> seatBookings = seatList.stream()
                    .map(seatNum -> {
                        SeatBooking sb = new SeatBooking();
                        sb.setShowId((long) ticket.getShowId());   // cast int showId to Long
                        sb.setSeatNumber(seatNum);

                        // Link this seat booking to the saved ticket
                        sb.setTicket(savedTicket);

                        return sb;
                    })
                    .collect(Collectors.toList());

            seatBookingRepo.saveAll(seatBookings);
        }

        return savedTicket;
    }

    // Get booking by ID
    public Ticket getBookingById(int id) {
        return bo.findById(id).orElse(null);
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return mb.findAll();
    }

    // Get movie by ID
    public Movie getMovieById(int id) {
        return mb.findById(id).orElse(null);
    }

    // Get all languages
    public List<String> getLanguages() {
        return mb.getLanguages();
    }


    // Get movies by language
    public List<Movie> getMoviesByLanguage(String lang) {
        return mb.getMoviesByLanguage(lang);
    }

    public Admin getUserByEmail(String email) {
        return ab.findById(email).orElse(null);
    }

    /**
     * Deletes booking and its associated seat bookings,
     * but ensures only the booking owner can delete it.
     * - User can delete their own bookings.
     * - Admin can delete only admin’s own bookings.
     */
    @Transactional
    public boolean deleteBooking(int bookingId, String requesterEmail) {
        Optional<Ticket> ticketOpt = bo.findById(bookingId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();

            // ✅ Allow delete only if the booking belongs to the requester
            if (ticket.getUseremail() != null && ticket.getUseremail().equals(requesterEmail)) {
                // Delete related seat bookings first
                seatBookingRepo.deleteByTicket_Bookingid((long) bookingId);

                // Delete the ticket itself
                bo.deleteById(bookingId);
                return true;
            }
        }
        return false;
    }

    // User login check
    public boolean checkUser(String email, String pass) {
        try {
            Admin user = ab.getById(email);
            return user.getEmail().equals(email) && user.getPassword().equals(pass);
        } catch (Exception e) {
            return false;
        }
    }

    public List<Ticket> getAllBookings() {
        return bo.findAll();
    }

    public List<Ticket> getBookingsByMobile(Long mobile) {
        return bo.findByMobilenumber(mobile);
    }

    public List<Ticket> getBookingsByUser(String email) {
        return bo.findByUseremail(email);
    }

    @Transactional
    public boolean deletemovie(int id) {
        try {
            mb.deleteById(id); // or your movie repo var
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
