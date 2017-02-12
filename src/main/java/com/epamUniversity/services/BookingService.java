package com.epamUniversity.services;
import com.epamUniversity.model.Auditorium;
import com.epamUniversity.model.Event;
import com.epamUniversity.model.Ticket;
import com.epamUniversity.model.User;
import org.joda.time.DateTime;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Yuriy_Tkach
 */
public interface BookingService {

    /**
     * Getting price when buying all supplied seats for particular event
     *
     * @param event
     *            Event to get base ticket price, vip seats and other
     *            information
     * @param dateTime
     *            Date and time of event air
     * @param user
     *            User that buys ticket could be needed to calculate discount.
     *            Can be <code>null</code>
     * @param seats
     *            Set of seat numbers that user wants to buy
     * @return total price
     */
    public double getTicketsPrice(@Nonnull Event event, @Nonnull DateTime dateTime, @Nullable User user,
                                  @Nonnull Set<Integer> seats);

    public double getTicketsPrice(@Nonnull Event event,
                                  @Nonnull Auditorium auditorium,
                                  @Nonnull DateTime dateTime,
                                  @Nullable User user,
                                  @Nonnull Integer seat);

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     *
     * @param tickets
     *            Set of tickets
     */
    public void bookTickets(@Nonnull Set<Ticket> tickets);

    public void bookTicket(@Nonnull Ticket tickets);

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param event
     *            Event to get tickets for
     * @param dateTime
     *            Date and time of airing of event
     * @return set of all purchased tickets
     */
    public @Nonnull Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull DateTime dateTime);

}
