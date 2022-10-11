package cinema.service;

import cinema.entity.Client;
import cinema.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class TicketService {

    private final HashMap<UUID, Client> reservedTicketsList;

    @Autowired
    public TicketService(HashMap<UUID, Client> reservedTicketsList) {
        this.reservedTicketsList = reservedTicketsList;
    }

    public Client reserveSeat(Seat seat) {
        seat.setPrice();
        Client client = new Client(UUID.randomUUID(), seat);
        if (reservedTicketsList.containsValue(client))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
        reservedTicketsList.put(client.getToken(), client);
        return client;
    }

    public Map<String, Seat> refundTicket(String reqToken) {
        reqToken = reqToken.split("\"")[3];
        UUID uuidToken = UUID.fromString(reqToken);
        Client client = reservedTicketsList.get(uuidToken);
        if (Objects.equals(client, null))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong token!");

        Map<String, Seat> returnTicketResp = new HashMap<>();
        returnTicketResp.put("returned_ticket", reservedTicketsList.get(uuidToken).getTicket());
        reservedTicketsList.remove(uuidToken);
        return returnTicketResp;
    }
}
