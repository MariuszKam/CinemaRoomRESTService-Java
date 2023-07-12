package cinema.business.dto;

import cinema.business.Cinema;
import cinema.business.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class DTOCinema {
    @JsonProperty("total_rows")
    private final int totalRows;
    @JsonProperty("total_columns")
    private final int totalColumns;
    @JsonProperty("available_seats")
    private final List<Seat> seats;

    public DTOCinema(Cinema cinema) {
        this.totalRows = cinema.getTotalRows();
        this.totalColumns = cinema.getTotalColumns();
        this.seats = cinema.getSeats().stream().filter(seat -> !seat.isPurchase()).toList();
    }
}
