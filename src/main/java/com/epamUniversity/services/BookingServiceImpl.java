package com.epamUniversity.services;

import com.epamUniversity.dao.UserRepository;
import com.epamUniversity.model.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.transaction.Transactional;
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
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TicketsService ticketsService;
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private DiscountServiceImpl discountService;
    @Autowired
    private Ticket ticket;
    private Event event;
    private User user;

    static final double VIPMULTIPLIER = 0.5;

    public double getTicketsPrice(@Nonnull Event event,
                                  @Nonnull Auditorium auditorium,
                                  @Nonnull DateTime dateTime,
                                  @Nullable User user,
                                  @Nonnull Integer seat) {
        double basePrice = event.getBasePrice();
        double priceMultiplier = getVipmultiplier(auditorium,seat);
        return basePrice * (1+priceMultiplier/100);
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull DateTime dateTime, @Nullable User user, @Nonnull Set<Integer> seats) {
        return 0;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

    }

    /**
     * Check that User has enought money and place is available
     * If everything is ok changes will be written into DB
     * @param ticket
     */
    @Transactional (Transactional.TxType.REQUIRES_NEW)
    public void bookTicket(@Nonnull Ticket ticket) {
        User user = ticket.getUser();
        accountService.chargeBalance(user,ticket.getPrice());
        ticketsService.save(ticket);
        user.addTicket(ticket);
        userRepository.saveAndFlush(user);
    }


    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event,
                                                   @Nonnull DateTime dateTime) {

        return null;
    }

    public double getTicketPrice(EventInstance event, DateTime date, int seat, User usr){
        ticket.setEvent(event);
        ticket.setSeat(seat);
        ticket.setUser(usr);
//        double price = getSeatPrice(event.getEventParent());
//        price *= 1 + DiscountServiceImpl.getBasePriceMultiplier(event,seat,usr);
//        ticket.setPrice(price);
        return 0.0;
    }

    public Ticket getTicket(EventInstance event, DateTime date, int seat, User usr){
        getTicketPrice(event,date,seat,usr);
        return ticket;
    }

    public double getVipmultiplier(Auditorium a, int seat){
        if(a.getVipSeats().contains(String.valueOf(seat)))
            return 20.0;
        else
            return 0.0;
    }


}