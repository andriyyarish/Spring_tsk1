package com.epamUniversity.services;

import com.epamUniversity.dao.TicketRepository;
import com.epamUniversity.model.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by Andriy_Yarish on 1/9/2017.
 */
@Service
public class TicketsService implements AbstractDomainObjectService<Ticket> {
    private static Map<Long,Ticket> tickets = new LinkedHashMap<>();

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket save(@Nonnull Ticket object) {
         return ticketRepository.save(object);
    }

    @Override
    public void remove(@Nonnull Ticket object) {
        ticketRepository.delete(object);
    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return tickets.get(id);
    }

    @Nonnull
    @Override
    public Collection<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket buildTicket(Event event, DateTime date, Auditorium auditorium, User user, int seat){

        EventInstance eventInstance = new EventInstance(event,auditorium,date);
        Ticket ticket = new Ticket(user,eventInstance, seat);
        return ticket;

    }

    public double calculatePrice(){
        return 0.0;
    }

}
