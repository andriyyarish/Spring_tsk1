package com.epamUniversity.controlers.restWS;

import com.epamUniversity.dao.EventRepository;
import com.epamUniversity.model.Event;
import com.epamUniversity.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Andriy_Yarish on 2/11/2017.
 */
@RestController
@RequestMapping(path = {"/rest/event"}, method = GET, produces = APPLICATION_JSON_VALUE)
public class EventResource {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;

    @RequestMapping
    public @ResponseBody Collection<Event> getAllEvents(){
        Collection<Event> eventList = eventService.getAll();
        return eventList;
    }

    @RequestMapping(value = "/{eventId}", method = GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Event getEventById(@PathVariable("eventId") Long id){
        Event event = eventService.getById(id);
        return event;
    }



}
