package cinema.business.dto;

import cinema.business.Seat;
import cinema.business.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOTicket {
    @JsonProperty("returned_ticket")
    private Seat ticket;

    public DTOTicket(Ticket ticket) {
        this.ticket = ticket.getTicket();
    }

    public Seat getTicket() {
        return ticket;
    }
}
