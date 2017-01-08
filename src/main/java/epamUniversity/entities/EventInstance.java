package epamUniversity.entities;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy_Yarish on 1/7/2017.
 */
public class EventInstance extends DomainObject {

    private Event eventParent;

    private Auditorium auditorium;

    private DateTime dateTime;

    List<Ticket> tiskets = new ArrayList<>();

    public Event getEventParent() {
        return eventParent;
    }

    public void setEventParent(Event eventParent) {
        this.eventParent = eventParent;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Ticket> getTiskets() {
        return tiskets;
    }

    public void setTiskets(List<Ticket> tiskets) {
        this.tiskets = tiskets;
    }
}
