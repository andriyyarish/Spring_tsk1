package epamUniversity.controlers;

import epamUniversity.entities.Auditorium;
import epamUniversity.entities.Event;
import epamUniversity.entities.User;
import epamUniversity.services.*;
import epamUniversity.util.DatesHandling;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static epamUniversity.util.DatesHandling.parseStringToDate;
import static java.lang.Integer.parseInt;
import static javax.xml.bind.DatatypeConverter.parseInteger;

/**
 * Created by Andriy_Yarish on 1/9/2017.
 */
@Controller
@RequestMapping(value = "booking")
public class BookingController {
    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @Autowired
    AuditoriumService auditoriumService;

    @RequestMapping (method = RequestMethod.GET)
    public ModelAndView showBookingForm(ModelAndView result){
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

        result.addObject("users",uls);
        result.addObject("events",eLs);
        result.addObject("auditoriums", aLs);
        result.setViewName("bookingFacade");
        return result;
    }

    @RequestMapping(value = "/ticketPrice", method = RequestMethod.POST)
    public ModelAndView getTicketPrice(ModelAndView result, HttpServletRequest request){
        String uEmail = request.getParameter("user");
        String aud = request.getParameter("auditorium");
        String ev = request.getParameter("event");
        int seat = parseInt(request.getParameter("seat"));
        DateTime date = parseStringToDate(request.getParameter("date"));

        User user = userService.getUserByEmail(uEmail);
        Auditorium auditorium = auditoriumService.getByName(aud);
        Event event1 = eventService.getByName(ev);

        if(event1.getAirDates().contains(date) && auditorium.getSeats()<=seat) {

            double price = ((BookingServiceImpl) bookingService).getTicketsPrice(event1, auditorium, new DateTime(), user, seat);
            result.addObject("price", price);
            result.addObject("event", ev);
            result.setViewName("ticketPrice");

        } else {
            result.addObject("message","Event does not run on choosen date or seat number is invalid");
            result.setViewName("error");
        }
        return result;
    }



}
