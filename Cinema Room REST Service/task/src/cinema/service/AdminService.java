package cinema.service;

import cinema.entity.Administrator;
import cinema.entity.CinemaRoom;
import cinema.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AdminService {

    private final Administrator admin;
    private final HashMap<UUID, Client> reservedTicketsList;
    private final CinemaRoom cinemaRoom;

    @Autowired
    public AdminService(Administrator admin, HashMap<UUID, Client> reservedTicketsList, CinemaRoom cinemaRoom) {
        this.admin = admin;
        this.reservedTicketsList = reservedTicketsList;
        this.cinemaRoom = cinemaRoom;
    }

    public Map<String, Integer> getStats(String password) {
        if (!Objects.equals(admin.getPassword(), password))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is wrong!");

        int currentIncome = reservedTicketsList.values().stream()
                .mapToInt(client -> client.getTicket().getPrice())
                .sum();
        int purchasedTickets = reservedTicketsList.size();
        int availableSets = cinemaRoom.getAvailableSeats().size() - purchasedTickets;

        admin.setStats("current_income", currentIncome);
        admin.setStats("number_of_available_seats", availableSets);
        admin.setStats("number_of_purchased_tickets", purchasedTickets);
        return admin.getStats();
    }
}
