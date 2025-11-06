package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.CreateShowRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.MovieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class MovieController {

    @Autowired private MovieService ms;
    @Autowired private AdminBo ab;
    @Autowired private BookingBo bo;
    @Autowired private TheatreRepository theatreRepo;
    @Autowired private ShowRepository showRepo;
    @Autowired private SeatRepository seatRepo;

    @Autowired private SeatBookingRepository seatBookingRepo;

    // Legacy JSP Routes
    @RequestMapping("/") public ModelAndView index() { return new ModelAndView("index"); }
    @RequestMapping("/addmovie") public ModelAndView addMovieForm() { return new ModelAndView("addmovie"); }

//    @PostMapping("/insertMovie")
//    public ModelAndView insertMovie(HttpServletRequest req) {
//        Movie m = new Movie(
//            0,
//            req.getParameter("mname"),
//            req.getParameter("director"),
//            req.getParameter("lang"),
//            LocalDate.parse(req.getParameter("rdate"))
//        );
//        boolean success = ms.insertMovie(m) != null;
//        return new ModelAndView(success ? "success1" : "error");
//    }
    
    @PostMapping("/insertMovie")
    public ModelAndView insertMovie(HttpServletRequest req) {
        Movie m = new Movie(
            0,
            req.getParameter("mname"),
            req.getParameter("director"),
            req.getParameter("lang"),
            LocalDate.parse(req.getParameter("rdate")),
            req.getParameter("poster"),   // ✅ new
            req.getParameter("duration"), // ✅ new
            req.getParameter("synopsis"), // ✅ new
            req.getParameter("cast"),     // ✅ new (JSON or comma-separated)
            req.getParameter("crew")      // ✅ new (JSON or comma-separated)
        );
        boolean success = ms.insertMovie(m) != null;
        return new ModelAndView(success ? "success1" : "error");
    }


    @GetMapping("/viewmovies") public ModelAndView viewMovies() {
        return new ModelAndView("viewmovies").addObject("movies", ms.getAllMovies());
    }

    @GetMapping("/register") public ModelAndView doRegister() { return new ModelAndView("register"); }

    @PostMapping("/insertUserr")
    public ModelAndView saveUser(@Valid Admin user, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                if (error instanceof FieldError fe) {
                    System.out.println("Validation Failed: " + fe.getField() + " - " + fe.getDefaultMessage());
                }
            });
            return new ModelAndView("register");
        }
        ab.save(user);
        return new ModelAndView("success1");
    }

    @RequestMapping("/login") public ModelAndView loginPage() { return new ModelAndView("login"); }

    @RequestMapping("/checkUser")
    public ModelAndView checkUser(@RequestParam String email, @RequestParam String pass) {
        boolean valid = ms.checkUser(email, pass);
        return new ModelAndView(valid ? "success" : "login");
    }

    // === REST APIs ===

    // Movies APIs
    @GetMapping("/api/movies") public List<Movie> getAllMovies() { return ms.getAllMovies(); }
    
//    @GetMapping("/api/movies/{id}") public Movie getMovieById(@PathVariable int id) { return ms.getMovieById(id); }
    
    @GetMapping("/api/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        System.out.println("Fetching movie with ID: " + id);
        Movie movie = ms.getMovieById(id);
        System.out.println("Found: " + movie);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    
    @PostMapping("/api/movies") public Movie addMovie(@RequestBody Movie movie) { return ms.insertMovie(movie); }
    @PostMapping("/api/movies/update") public Movie updateMovie(@RequestBody Movie movie) { return ms.updateMovie(movie); }
    @DeleteMapping("/api/movies/{id}") public String deleteMovie(@PathVariable int id) {
        ms.deletemovie(id);
        return "Deleted successfully";
    }

    // === Booking APIs ===

//    @DeleteMapping("/api/booking/{id}")
//    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
//        try {
//            deleteBookingAndSeatBookings(id);
//            return ResponseEntity.ok("Booking deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
//        }
//    }
    
 // === Booking APIs ===
    @DeleteMapping("/api/booking/{id}")
    public ResponseEntity<?> deleteBooking(
            @PathVariable int id,
            @RequestParam String email // requester email must be passed
    ) {
        boolean deleted = ms.deleteBooking(id, email);

        if (deleted) {
            return ResponseEntity.ok("Booking deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not allowed to delete this booking");
        }
    }


    @PostMapping("/api/booking") 
    public Ticket bookTicket(@RequestBody Ticket ticket) { 
        return ms.bookmovie(ticket); 
    }

    @GetMapping("/api/bookings") 
    public List<Ticket> getUserBookings(@RequestParam String email) {
        return ms.getBookingsByUser(email);
    }

    @GetMapping("/api/bookings/all")
    public List<Ticket> getAllBookings() {
        return ms.getAllBookings();
    }

    @GetMapping("/api/booking/{id}")
    public ResponseEntity<?> getBookingDetails(@PathVariable int id) {
        Ticket ticket = ms.getBookingById(id);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }

        // Fetch related data
        Optional<Show> showOpt = showRepo.findById((long) ticket.getShowId());
        if (showOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Show not found");
        }

        Show show = showOpt.get();
        Optional<Theatre> theatreOpt = theatreRepo.findById(show.getTheatreId());
        String theatreName = theatreOpt.map(Theatre::getName).orElse("Unknown");

        List<String> seatNumbers = Arrays.asList(ticket.getSeatnumbers().split(","));

        // Construct DTO
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("bookingId", ticket.getBookingid());
        res.put("cusName", ticket.getCusname());
        res.put("mobileNumber", ticket.getMobilenumber());
        res.put("numberOfTickets", ticket.getNumberofticket());
        res.put("movieName", ticket.getMoviename());
        res.put("theatreName", theatreName);
        res.put("showDate", ticket.getShowdate());
        res.put("showTime", ticket.getShowtime());
        res.put("seatNumbers", seatNumbers);

        return ResponseEntity.ok(res);
    }

    @Transactional
    public void deleteBookingAndSeatBookings(int bookingId) {
        Ticket ticket = bo.findById(bookingId).orElse(null);
        if (ticket != null) {
            // Parse the booked seats string
            List<String> seatList = Arrays.asList(ticket.getSeatnumbers().split("\\s*,\\s*"));

            // Delete seat bookings for showId and seat numbers
            seatBookingRepo.deleteByShowIdAndSeatNumberIn((long) ticket.getShowId(), seatList);

            // Delete the ticket itself
            bo.deleteById(bookingId);
        } else {
            throw new RuntimeException("Booking not found");
        }
    }

    // === Theatre APIs ===
    @GetMapping("/api/theatres") public List<Theatre> getAllTheatres() { return theatreRepo.findAll(); }
    @GetMapping("/api/theatres/{id}")
    public Theatre getTheatre(@PathVariable Long id) {
        return theatreRepo.findById(id).orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

    // === Show APIs ===
    @GetMapping("/api/shows")
    public List<Show> getShowsByMovieAndDate(@RequestParam Long movieId, @RequestParam String date) {
        return showRepo.findByMovieIdAndShowDate(movieId, LocalDate.parse(date));
    }

    @GetMapping("/api/shows/by-theatre")
    public List<Show> getShowsByTheatreAndDate(@RequestParam Long theatreId, @RequestParam String date) {
        return showRepo.findByTheatreIdAndShowDate(theatreId, LocalDate.parse(date));
    }

    @GetMapping("/api/shows/by-id")
    public ResponseEntity<Map<String, Object>> getShowById(@RequestParam Long showId) {
        Optional<Show> showOpt = showRepo.findById(showId);
        if (showOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Show show = showOpt.get();
        Optional<Theatre> theatreOpt = theatreRepo.findById(show.getTheatreId());
        String theatreName = theatreOpt.map(Theatre::getName).orElse("Unknown Theatre");

        Map<String, Object> res = new HashMap<>();
        res.put("showId", show.getId());
        res.put("movieId", show.getMovieId());
        res.put("showDate", show.getShowDate());
        res.put("showTime", show.getShowTime());
        res.put("theatreId", show.getTheatreId());
        res.put("theatreName", theatreName);

        return ResponseEntity.ok(res);
    }

    // === Seat APIs ===
    @GetMapping("/api/seats")
    public List<Seat> getAllSeats() {
        return seatRepo.findAllByOrderByRowAscSeatNoAsc();
    }

    @GetMapping("/api/seats/reserved")
    public List<String> getReservedSeats(@RequestParam Long showId) {
        return seatBookingRepo.findSeatNumberByShowId(showId);
    }

    @PostMapping("/api/seats/book")
    @Transactional
    public ResponseEntity<String> bookSeats(@RequestParam Long showId, @RequestBody List<String> seatNumbers) {
        List<String> alreadyBooked = seatBookingRepo.findSeatNumberByShowIdAndSeatNumberIn(showId, seatNumbers);
        if (!alreadyBooked.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Seats already booked: " + String.join(", ", alreadyBooked));
        }

        List<SeatBooking> bookings = seatNumbers.stream()
                .map(seatNumber -> {
                    SeatBooking sb = new SeatBooking();
                    sb.setShowId(showId);
                    sb.setSeatNumber(seatNumber);
                    return sb;
                })
                .collect(Collectors.toList());

        seatBookingRepo.saveAll(bookings);
        return ResponseEntity.ok("Seats booked successfully");
    }

    // === Login API ===
    @PostMapping("/api/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String pass = payload.get("pass");
        boolean valid = ms.checkUser(email, pass);

        Map<String, Object> res = new HashMap<>();
        res.put("success", valid);
        if (valid) {
            Admin user = ms.getUserByEmail(email); // fetch full user
            res.put("user", Map.of(
                "email", user.getEmail(),
                "name", user.getName(),
                "userType", user.getUserType()
            ));
        }
        return res;
    }

    // === Bulk Create/Update Showtimes ===
    @PostMapping("/api/shows/create")
    @Transactional
    public ResponseEntity<String> createOrUpdateShowTimes(@RequestBody CreateShowRequest request) {
        Long movieId = request.getMovieId();
        LocalDate showDate = LocalDate.parse(request.getShowDate());

        Set<String> requestedShowKeys = new HashSet<>();
        for (CreateShowRequest.TheatreShow theatre : request.getTheatres()) {
            Long theatreId = theatre.getTheatreId();
            for (String showTime : theatre.getShowtimes()) {
                requestedShowKeys.add(theatreId + "|" + showTime);
            }
        }

        List<Show> existingShows = showRepo.findByMovieIdAndShowDate(movieId, showDate);

        for (Show existingShow : existingShows) {
            String key = existingShow.getTheatreId() + "|" + existingShow.getShowTime();
            if (!requestedShowKeys.contains(key)) {
                showRepo.delete(existingShow);
            }
        }

        Set<String> existingShowKeys = existingShows.stream()
                .map(s -> s.getTheatreId() + "|" + s.getShowTime())
                .collect(Collectors.toSet());

        for (CreateShowRequest.TheatreShow theatre : request.getTheatres()) {
            Long theatreId = theatre.getTheatreId();
            for (String showTime : theatre.getShowtimes()) {
                String key = theatreId + "|" + showTime;
                if (!existingShowKeys.contains(key)) {
                    Show newShow = new Show();
                    newShow.setMovieId(movieId);
                    newShow.setTheatreId(theatreId);
                    newShow.setShowTime(showTime);
                    newShow.setShowDate(showDate);
                    showRepo.save(newShow);
                }
            }
        }

        return ResponseEntity.ok("Showtimes updated successfully!");
    }
    
    @GetMapping("/api/languages")
    public List<String> getLanguages() {
        return ms.getLanguages();
    }
    
 // === Movies By Language ===
    @GetMapping("/api/movies/lang/{lang}")
    public List<Movie> getMoviesByLanguage(@PathVariable String lang) {
        return ms.getMoviesByLanguage(lang);
    }



}
