package com.epamUniversity.controlers.restWS;

import com.epamUniversity.controlers.restWS.dto.TicketRequest;
import com.epamUniversity.controlers.restWS.dto.TicketResponse;
import com.epamUniversity.model.Event;
import com.epamUniversity.model.EventInstance;
import com.epamUniversity.model.Ticket;
import com.epamUniversity.model.User;
import com.epamUniversity.services.AuditoriumService;
import com.epamUniversity.services.BookingService;
import com.epamUniversity.services.EventService;
import com.epamUniversity.services.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Andriy_Yarish on 2/12/2017.
 */
@Controller
@RequestMapping("/rest/booking")
public class BookingResource {
    @Autowired
    BookingService bookingService;
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    AuditoriumService auditoriumService;

    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public @ResponseBody double getTicketPrice(@RequestBody TicketRequest request){
        Event event = eventService.getById(request.getEventId());
//        return bookingService.getTicketsPrice();
        return event.getBasePrice();
    }

    // {"eventId":1, "userId":2, "seat":1}

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public @ResponseBody TicketResponse buyTicket(@RequestBody TicketRequest request){
        Event event = eventService.getById(request.getEventId());
        EventInstance eventInstance = new EventInstance(event
                ,auditoriumService.getByName("Blockbaster")
                ,new DateTime());
        User user = userService.getById(request.getUserId());
        Ticket ticket = new Ticket(user,eventInstance,request.getSeat().intValue());
        bookingService.bookTicket(ticket);
        return new TicketResponse(ticket);
    }
}
