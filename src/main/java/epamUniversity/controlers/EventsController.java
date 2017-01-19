package epamUniversity.controlers;

import epamUniversity.model.Auditorium;
import epamUniversity.model.Event;
import epamUniversity.services.AuditoriumServiceImpl;
import epamUniversity.services.EventService;
import epamUniversity.util.DatesHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static epamUniversity.util.DatesHandling.parseStringToDate;

/**
 * Created by Andriy_Yarish on 12/30/2016.
 */
@Controller
public class EventsController {
    @Autowired
    private EventService eventService;
    //todo change for interface
    @Autowired
    private AuditoriumServiceImpl auditoriumService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String getEvents(@ModelAttribute("model") ModelMap modelMap) {
        modelMap.put("eventList", eventService.getAll());
        modelMap.put("auditoriumsList", auditoriumService.getAuditoriums());
        return "eventsFacade";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event) {
        if (null != event.getName() && null != event.getEventRating())
            eventService.save(event);
        return "redirect:events.html";
    }

    @RequestMapping(value = "/events/assignAuditorium")
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
        if(assigned)
            return "redirect:/events.html";
        else
            return "error";
    }

    @RequestMapping(value = "events/airDates")
    public String addAirDate(HttpServletRequest request){
        boolean isAdded = false;
        String eventName = request.getParameter("event");
        String airDate = request.getParameter("date");

        Event event = eventService.getByName(eventName);
        isAdded = event.addAirDateTime(DatesHandling.parseStringToDate(airDate));
        if(isAdded)
            return "redirect:/events.html";
        else
            return "error"; //TODO need to throw some message as an option ModelAndView should be returned;
    }

}
