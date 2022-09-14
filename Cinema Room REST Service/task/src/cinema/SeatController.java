package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
public class SeatController {

    private final CinemaRoom cinemaRoom;
    private final Map<UUID, Client> reservedTicketsMap;

    public SeatController() {
        this.cinemaRoom = new CinemaRoom(9, 9);
        reservedTicketsMap = new HashMap<>();
    }

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public Client reserveSeat(@Valid @RequestBody Seat seat) {
        seat.setPrice();
        Client client = new Client(UUID.randomUUID(), seat);
        if (reservedTicketsMap.containsValue(client))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
        reservedTicketsMap.put(client.getToken(), client);
        return client;
    }

    @PostMapping("/return")
    public Map<String, Seat> returnTicket(@RequestBody String reqToken) {
        reqToken = reqToken.split("\"")[3];
        UUID uuidToken = UUID.fromString(reqToken);

        Client client = reservedTicketsMap.get(uuidToken);
        if (Objects.equals(client, null))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong token!");

        Map<String, Seat> returnTicketResp = new HashMap<>();
        returnTicketResp.put("returned_ticket", reservedTicketsMap.get(uuidToken).getTicket());
        reservedTicketsMap.remove(uuidToken);
        return returnTicketResp;
    }

    @PostMapping("/stats")
    public Map<String, Integer> getStats(@RequestParam(required = false) String password) {
        Administrator admin = new Administrator(new HashMap<>());
        if (!Objects.equals(admin.getPassword(), password) || Objects.equals(password, null))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is wrong!");

        int currentIncome = reservedTicketsMap.values().stream()
                .mapToInt(client -> client.getTicket().getPrice())
                .sum();
        int purchasedTickets = reservedTicketsMap.size();
        int availableSets = cinemaRoom.getAvailableSeats().size() - purchasedTickets;

        admin.setStats("current_income", currentIncome);
        admin.setStats("number_of_available_seats", availableSets);
        admin.setStats("number_of_purchased_tickets", purchasedTickets);
        return admin.getStats();
    }
}
