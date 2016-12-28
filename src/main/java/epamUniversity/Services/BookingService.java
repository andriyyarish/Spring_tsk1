package epamUniversity.Services;

import epamUniversity.Entities.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

import static epamUniversity.Entities.Rating.LOW;

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

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private DiscountService discountService;
    Event event;
    User user;
    Ticket ticket;
    static final double VIPMULTIPLIER = 0.5;

    public double getTicketPrice(Event event, DateTime date, int seat, User usr){
        ticket.setEvent(event);
        ticket.setSeat(seat);
        ticket.setOwner(usr.getName());
        double price = getSeatPrice(event);
        price *= (1 + getSeatTypeMultiplier(event,seat) + getRatingMultiplier(event)) - getBirthdayDiscount(usr);
        ticket.setPrice(price);
        return price;
    }

    public Ticket getTicket(Event event, DateTime date, int seat, User usr){
        getTicketPrice(event,date,seat,usr);
        return ticket;
    }

    private double getBirthdayDiscount(User user){
        double discount = 0.0;
        DateTime today = DateTime.now();
        DateTime usrBirtday = user.getDateOfBirth();

        if(today.getDayOfMonth()==usrBirtday.getDayOfMonth()
                && today.getMonthOfYear()==usrBirtday.getDayOfYear())
            discount = 0.15;

        return discount;
    }

    private double getSeatTypeMultiplier(Event event, int seat){
        if (event.getAuditorium().getVipSeats().contains(seat))
            return VIPMULTIPLIER;
        else
            return 1.0;
    }

    private double getRatingMultiplier(Event event){

        switch (event.getRating()){
            case LOW:
                return 0.0;
            case MEDIUM:
                return 0.2;
            case HIGH:
                return 0.5;
            default:
                return 0.0;
        }
    }

    private double getSeatPrice(Event event){
        return event.getPrice();
    }








}
