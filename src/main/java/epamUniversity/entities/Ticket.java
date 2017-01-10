package epamUniversity.entities;

import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class Ticket extends DomainObject implements Comparable<Ticket> {

    private int index;

    private User user;

    private EventInstance event;

    private DateTime dateTime;

    private int seat;

    private double price;

    public Ticket(User user, EventInstance event, DateTime dateTime, int seat) {
        this();
        this.user = user;
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
    }

    public Ticket(User user, Event event,Auditorium auditorium, DateTime dateTime, int seat) {
        this();
        this.user = user;
        this.event = new EventInstance(event,auditorium,dateTime);
        this.dateTime = dateTime;
        this.seat = seat;
    }

    private Ticket() {
        super.setId(index++);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventInstance getEvent() {
        return event;
    }

    public void setEvent(EventInstance event) {
        this.event = event;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, event, seat);
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
        Ticket other = (Ticket) obj;
        if (dateTime == null) {
            if (other.dateTime != null) {
                return false;
            }
        } else if (!dateTime.equals(other.dateTime)) {
            return false;
        }
        if (event == null) {
            if (other.event != null) {
                return false;
            }
        } else if (!event.equals(other.event)) {
            return false;
        }
        if (seat != other.seat) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = dateTime.compareTo(other.getDateTime());

        if (result == 0) {
            result = event.getEventParent().getName().compareTo(other.getEvent().getEventParent().getName());
        }
        if (result == 0) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }
}
