package cinema.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private final int row;
    private final int column;
    private final int price;
    @JsonIgnore
    private boolean purchase;


    public Seat(@JsonProperty("row") int row,@JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        if (row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
        this.purchase = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPurchase() {
        return purchase;
    }

    public void purchased() {
        this.purchase = true;
    }

    public void refund() {this.purchase = false;}


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
        Seat seat = (Seat) obj;
        return seat.getColumn() == this.getColumn() &&
                seat.getRow() == this.getRow() &&
                seat.isPurchase() == this.isPurchase();
    }
}
