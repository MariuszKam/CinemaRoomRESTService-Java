package cinema.business;


import java.util.ArrayList;

public class Cinema {
    private final int totalRows;
    private final int totalColumns;
    private final ArrayList<Seat> seats;

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns;j++) {
                seats.add(new Seat(i,j));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }


    public ArrayList<Seat> getSeats() {
        return seats;
    }

}
