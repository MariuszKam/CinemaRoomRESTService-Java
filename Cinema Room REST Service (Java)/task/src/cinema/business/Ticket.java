package cinema.business;

import java.util.UUID;

public class Ticket {
    private final String token;
    private final Seat ticket;

    public Ticket(Seat ticket) {
        this.token = UUID.randomUUID().toString();
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) obj;
        return this.token.equals(ticket.token);
    }

    public Seat getTicket() {
        return ticket;
    }
}
