package cinema.presentation;


import cinema.business.*;
import cinema.business.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private final Cinema cinema = new Cinema(9, 9);
    private final List<Ticket> tickets = new ArrayList<>();
    private final Statistics statistics = new Statistics();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/seats")
    public DTOCinema cinema() {
        return new DTOCinema(cinema);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase (@RequestBody Seat seat) {
        if ((!(seat.getRow() > 0 && seat.getRow() <= cinema.getTotalRows())) ||
                (!(seat.getColumn() > 0 && seat.getColumn() <= cinema.getTotalColumns()))) {
            return new ResponseEntity<>(new ErrorResponse("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        if (cinema.getSeats().contains(seat)) {
            for (Seat seatObj:cinema.getSeats()
                 ) {
                if (seatObj.equals(seat)) {
                    seatObj.purchased();
                    Ticket ticket = new Ticket(seatObj);
                    tickets.add(ticket);
                    return new ResponseEntity<>(ticket, HttpStatus.OK);
                }
            }
        } else {
            return new ResponseEntity<>(new ErrorResponse("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refund(@RequestBody String token) throws JsonProcessingException {
        DTOToken dtoToken = objectMapper.readValue(token, DTOToken.class);
        for (Ticket ticket:tickets
             ) {
            if (ticket.getToken().equals(dtoToken.getToken())) {
                for (Seat seat: cinema.getSeats()
                     ) {
                    if (seat.equals(ticket.getTicket())) {
                        seat.refund();
                        break;
                    }
                }
                tickets.remove(ticket);
                return new ResponseEntity<>(new DTOTicket(ticket), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>((new ErrorResponse("Wrong token!")), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> statistic (@RequestParam(value = "password", required = false) String password) {
        if ("super_secret".equals(password)) {
            statistics.calculate(tickets, cinema);
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/tickets")
    public List<Ticket> tickets() {
        return tickets;
    }


}
