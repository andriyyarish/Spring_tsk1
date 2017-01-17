package epamUniversity.model;

import epamUniversity.services.EventService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy_Yarish on 1/7/2017.
 */
public class EventInstance extends DomainObject {

    @Autowired
    private EventService eventService;

    private Event eventParent;

    private Auditorium auditorium;

    private DateTime dateTime;

    List<Ticket> tickets = new ArrayList<>();

    public EventInstance(Event eventParent, Auditorium auditorium, DateTime dateTime) {
        this.eventParent = eventParent;
        this.auditorium = auditorium;
        this.dateTime = dateTime;
        //todo need to register eventInstances and link to event, available seats should be checked there
    }

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
