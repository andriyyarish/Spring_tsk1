package epamUniversity.controlers;

import epamUniversity.services.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andriy_Yarish on 12/30/2016.
 */
@Controller
public class EventsController {
    @Autowired
    EventServiceImpl eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String getEvents(@ModelAttribute("model") ModelMap modelMap){
        modelMap.put("eventList", eventService.getAll());
        return "events";
    }

}
