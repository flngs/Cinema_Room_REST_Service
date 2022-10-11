package cinema.controller;

import cinema.entity.CinemaRoom;
import cinema.entity.Client;
import cinema.entity.Seat;
import cinema.service.AdminService;
import cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class SeatController {

    private final CinemaRoom cinemaRoom;
    private final AdminService adminService;
    private final TicketService ticketService;

    @Autowired
    public SeatController(CinemaRoom cinemaRoom, AdminService adminService, TicketService ticketService) {
        this.cinemaRoom = cinemaRoom;
        this.adminService = adminService;
        this.ticketService = ticketService;
    }

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public Client reserveSeat(@Valid @RequestBody Seat seat) {
        return ticketService.reserveSeat(seat);
    }

    @PostMapping("/return")
    public Map<String, Seat> returnTicket(@RequestBody String reqToken) {
        return ticketService.refundTicket(reqToken);
    }

    @PostMapping("/stats")
    public Map<String, Integer> getStats(@RequestParam(required = false) String password) {
        return adminService.getStats(password);
    }
}
