package epamUniversity.controlers;

import epamUniversity.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Andriy_Yarish on 12/30/2016.
 */
@Controller
public class EventsController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String getEvents(@ModelAttribute("model") ModelMap modelMap){
        modelMap.put("eventList", eventService.getEventMap().values());
        return "events";
    }

}
