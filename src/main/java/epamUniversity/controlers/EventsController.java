package epamUniversity.controlers;

import epamUniversity.entities.Auditorium;
import epamUniversity.entities.Event;
import epamUniversity.services.AuditoriumService;
import epamUniversity.services.AuditoriumServiceImpl;
import epamUniversity.services.EventService;
import epamUniversity.services.EventServiceImpl;
import epamUniversity.util.DatesHandling;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static epamUniversity.util.DatesHandling.parseStringToDate;

/**
 * Created by Andriy_Yarish on 12/30/2016.
 */
@Controller
public class EventsController {
    @Autowired
    EventService eventService;
    //todo change for interface
    @Autowired
    AuditoriumServiceImpl auditoriumService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String getEvents(@ModelAttribute("model") ModelMap modelMap) {
        modelMap.put("eventList", eventService.getAll());
        modelMap.put("auditoriumsList", auditoriumService.getAuditoriums());
        return "events";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event) {
        if (null != event.getName() && null != event.getEventRating())
            eventService.save(event);
        return "redirect:events.html";
    }

    @RequestMapping(value = "/assignAuditoriumToEvent")
    public String assignAuditorium(HttpServletRequest request) {
        boolean assigned = false;
        String eventName = request.getParameter("event");
        String auditName = request.getParameter("auditorium");
        String date = request.getParameter("date");

        Auditorium auditorium;
        Event event;
        if (eventName != null && auditName != null) {
            auditorium = auditoriumService.getAuditorium(auditName);
            event = eventService.getByName(eventName);
            assigned = event.assignAuditorium(parseStringToDate(date), auditorium); //TODO Need to show somehow the result
        }
        return "redirect:events.html";
    }

}
