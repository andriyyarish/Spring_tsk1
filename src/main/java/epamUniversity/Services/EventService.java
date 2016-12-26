package epamUniversity.Services;

import epamUniversity.Entities.Auditorium;
import epamUniversity.Entities.Event;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class EventService {

    private static Map<Integer, Event> eventList = new LinkedHashMap<Integer, Event>();
    private static int index = 0;

    public void addEvent(Event event){
        eventList.put(index,event);
        index ++;
    }

    public Map<Integer,Event> getEventMap (){
        return eventList;
    }

    public Event getEventById(int id){
        return eventList.get(id);
    }

    public Map<Integer, Event> getForDateRange(Date from, Date to){
        Map<Integer, Event> filteredEvents = new HashMap<>();

        for (Map.Entry<Integer, Event> e: eventList.entrySet()){
            Date date = (e.getValue()).getDate();
            if(date.after(from) && date.before(to))
                filteredEvents.put(e.getKey(),e.getValue());
        }
        return filteredEvents;
    }

    public Map<Integer, Event> getNextEvents(Date to){
        return getForDateRange(new Date(), to);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date){
        event.setAuditorium(auditorium);
        event.setDate(date);
    }

    public void removeEventByIndex(int key) {

        if(eventList.remove(index)==null)
            System.out.println("!!! Caution - remove operation failed. There are no value mapped to this key");

    }
}
