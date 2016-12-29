package epamUniversity.Entities;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class Ticket {
    private double price;
    private int seat;
    private SeatType seatType;
    private Event event;
    private String owner;

    public Ticket(){

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", seat=" + seat +
                ", seatType=" + seatType +
                ", event=" + event +
                ", owner='" + owner + '\'' +
                '}';
    }
}
