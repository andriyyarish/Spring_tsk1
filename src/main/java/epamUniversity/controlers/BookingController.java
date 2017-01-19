package epamUniversity.controlers;

import epamUniversity.model.Auditorium;
import epamUniversity.model.Event;
import epamUniversity.model.Ticket;
import epamUniversity.model.User;
import epamUniversity.services.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static epamUniversity.util.DatesHandling.containsDate;
import static epamUniversity.util.DatesHandling.parseStringToDate;
import static java.lang.Integer.parseInt;

/**
 * Created by Andriy_Yarish on 1/9/2017.
 */
@Controller
@RequestMapping(value = "booking")
public class BookingController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showBookingForm(ModelAndView result) {
        List<String> uls = userService.getAll()
                .stream()
                .map(user -> user.getEmail())
                .collect(Collectors.toList());

        List<String> eLs = eventService.getAll()
                .stream()
                .map(event -> event.getName())
                .collect(Collectors.toList());


        List<String> aLs = auditoriumService.getAll()
                .stream()
                .map(auditorium -> auditorium.getName())
                .collect(Collectors.toList());

        result.addObject("users", uls);
        result.addObject("events", eLs);
        result.addObject("auditoriums", aLs);
        result.setViewName("bookingFacade");
        return result;
    }

    @RequestMapping(value = "/ticketPrice", method = RequestMethod.POST)
    public ModelAndView getTicketPrice(ModelAndView result, HttpServletRequest request) {
        String uEmail = request.getParameter("user");
        String aud = request.getParameter("auditorium");
        String ev = request.getParameter("event");
        int seat = parseInt(request.getParameter("seat"));
        DateTime date = parseStringToDate(request.getParameter("date"));

        User user = userService.getUserByEmail(uEmail);
        Auditorium auditorium = auditoriumService.getByName(aud);
        Event event = eventService.getByName(ev);

        if (containsDate(event.getAirDates(), date) && auditorium.getSeats() >= seat) {

            double price = ((BookingServiceImpl) bookingService).getTicketsPrice(event, auditorium, new DateTime(), user, seat);
            result.addObject("price", price);
            result.addObject("event", ev);
            result.addObject("auditorium", aud);
            result.addObject("seat", seat);
            result.addObject("date", date);
            result.addObject("user", uEmail);
            result.setViewName("ticketPrice");

        } else {
            result.addObject("message", "Event does not run on choosen date or seat number is invalid");
            result.setViewName("error");
        }
        return result;
    }

    @RequestMapping(value = "/orderTicket", method = RequestMethod.POST)
    public ModelAndView orderTicket(ModelAndView result, HttpServletRequest request) {

        ModelAndView modelAndView = getTicketPrice(result, request);
        User u = userService.getUserByEmail((String) modelAndView.getModel().get("user"));
        Event e = eventService.getByName((String) modelAndView.getModel().get("event"));
        Auditorium a = auditoriumService.getByName((String) modelAndView.getModel().get("auditorium"));
        DateTime d = (DateTime) modelAndView.getModel().get("date");
        Integer s = (Integer) modelAndView.getModel().get("seat");

        Ticket ticket = new Ticket(u, e, a, d, s);
        ticket.setPrice((Double) modelAndView.getModel().get("price"));

        u.addTicket(ticket);
        ticketsService.save(ticket);


        result.addObject("ticket",ticket);
        result.setViewName("ticketPdfView");
        return result;
    }




}
