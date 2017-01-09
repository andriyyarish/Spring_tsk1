package epamUniversity.services;

import epamUniversity.entities.*;
import epamUniversity.services.AbstractDomainObjectService;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by Andriy_Yarish on 1/9/2017.
 */
public class TicketsService implements AbstractDomainObjectService<Ticket> {
    private static Map<Integer,Ticket> tickets = new LinkedHashMap<Integer,Ticket>();

    @Override
    public Ticket save(@Nonnull Ticket object) {
         tickets.put(object.getId(),object);
         return tickets.get(object.getId());
    }

    @Override
    public void remove(@Nonnull Ticket object) {
        tickets.remove(object.getId());
    }

    @Override
    public Ticket getById(@Nonnull Integer id) {
        return tickets.get(id);
    }

    @Nonnull
    @Override
    public Collection<Ticket> getAll() {
        return tickets.values();
    }

    public Ticket buildTicket(Event event, DateTime date, Auditorium auditorium, User user, int seat){

        EventInstance eventInstance = new EventInstance(event,auditorium,date);
        Ticket ticket = new Ticket(user,eventInstance, date, seat);
        return ticket;

    }

    public double calculatePrice(){
        return 0.0;
    }

}
