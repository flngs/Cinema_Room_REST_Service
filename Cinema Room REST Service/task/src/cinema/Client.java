package cinema;

import java.util.Objects;
import java.util.UUID;

public class Client {

    private UUID token;
    private Seat ticket;

    public Client(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(ticket, client.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }
}
