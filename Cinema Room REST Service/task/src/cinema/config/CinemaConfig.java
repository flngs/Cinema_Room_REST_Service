package cinema.config;

import cinema.entity.Administrator;
import cinema.entity.CinemaRoom;
import cinema.entity.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.UUID;

@Configuration
public class CinemaConfig {

    @Bean
    public CinemaRoom getCinemaRoom() {
        return new CinemaRoom(9, 9);
    }

    @Bean
    public HashMap<UUID, Client> getReservedTicketsList() {
        return new HashMap<>();
    }

    @Bean
    public Administrator getAdministrator() {
        return new Administrator("super_secret", new HashMap<>());
    }
}
