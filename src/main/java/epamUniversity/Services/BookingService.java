package epamUniversity.Services;

import epamUniversity.Entities.Event;
import epamUniversity.Entities.User;

import java.util.Date;

/**
 * BookingService - Manages tickets, prices, bookings

 getTicketPrice(event, date, time, seats, user) - returns price for ticket for specified event on specific date and time for specified seats.
 User is needed to calculate discount (see below)
 Event is needed to get base price, rating
 Vip seats should cost more than regular seats (For example, 2xBasePrice)
 All prices for high rated movies should be higher (For example, 1.2xBasePrice)
 bookTicket(user, ticket) - user could  be registered or not. If user is registered, then booking information is stored for that user. Purchased tickets for particular event should be stored
 getTicketsForEvent(event, date) - get all purchased tickets for event for specific date
 */
public class BookingService {

    EventService eventService;
    UserService userService;
    DiscountService discountService;

    public int getTicketPrice(Event event, Date date, int seat, User usr){
        return 0;
    }
}
