package epamUniversity.services;

import epamUniversity.entities.*;
import epamUniversity.services.discount.DiscountServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * BookingServiceImpl - Manages tickets, prices, bookings

 getTicketPrice(event, date, time, seats, user) - returns price for ticket for specified event on specific date and time for specified seats.
 User is needed to calculate discount (see below)
 Event is needed to get base price, rating
 Vip seats should cost more than regular seats (For example, 2xBasePrice)
 All prices for high rated movies should be higher (For example, 1.2xBasePrice)
 bookTicket(user, ticket) - user could  be registered or not. If user is registered, then booking information is stored for that user. Purchased tickets for particular event should be stored
 getTicketsForEvent(event, date) - get all purchased tickets for event for specific date
 */
public class BookingServiceImpl implements BookingService {

    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private UserServiceImpl userServiceImpl;
//    @Autowired
//    private DiscountServiceImpl discountService;
    @Autowired
    private Ticket ticket;
    private Event event;
    private User user;

    static final double VIPMULTIPLIER = 0.5;

    public double getTicketPrice(EventInstance event, DateTime date, int seat, User usr){
        ticket.setEvent(event);
        ticket.setSeat(seat);
        ticket.setUser(usr);
        double price = getSeatPrice(event.getEventParent());
//        price *= 1 + DiscountServiceImpl.getBasePriceMultiplier(event,seat,usr);
        ticket.setPrice(price);
        return price;
    }

    public Ticket getTicket(EventInstance event, DateTime date, int seat, User usr){
        getTicketPrice(event,date,seat,usr);
        return ticket;
    }

    private double getSeatPrice(Event event){
        return event.getBasePrice();
    }


    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull DateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return 0;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull DateTime dateTime) {
        return null;
    }
}
