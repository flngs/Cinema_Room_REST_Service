package cinema.entity;

import org.hibernate.validator.constraints.Range;

import java.util.Objects;

public class Seat {

    @Range(min = 1, max = 9)
    private int row;
    @Range(min = 1, max = 9)
    private int column;
    private int price;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
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

    public void setPrice() {
        this.price = this.row <= 4 ? 10 : 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}