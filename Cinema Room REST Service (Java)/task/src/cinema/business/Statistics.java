package cinema.business;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Statistics {
    @JsonProperty("current_income")
    private int income;
    @JsonProperty("number_of_available_seats")
    private int seats;
    @JsonProperty("number_of_purchased_tickets")
    private int tickets;

    public Statistics() {
        this.income = 0;
        this.seats = 0;
        this.tickets = 0;
    }

    public int getIncome() {
        return income;
    }

    public int getSeats() {
        return seats;
    }

    public int getTickets() {
        return tickets;
    }


    public void calculate(List<Ticket> tickets, Cinema cinema) {
        this.income = 0;
        for (Ticket ticket:tickets
             ) {
            System.out.println("Before: " + this.income);
            this.income += ticket.getTicket().getPrice();
            System.out.println("After: " + this.income);
        }
        this.seats = cinema.getSeats().size() - tickets.size();
        this.tickets = tickets.size();

    }


}
