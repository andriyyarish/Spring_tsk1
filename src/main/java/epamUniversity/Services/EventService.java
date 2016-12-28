package epamUniversity.services;

import org.joda.time.DateTime;

import epamUniversity.entities.Auditorium;
import epamUniversity.entities.Event;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class EventService {

    private static Map<Integer, Event> eventList = new LinkedHashMap<Integer, Event>();
    private static int index = 0;

    public int addEvent(Event event){
        eventList.put(index,event);
        return index++ ;
    }

    public Map<Integer,Event> getEventMap (){
        return eventList;
    }

    public Event getEventById(int id){
        return eventList.get(id);
    }

    public Map<Integer, Event> getForDateRange(DateTime from, DateTime to){
        Map<Integer, Event> filteredEvents = new HashMap<>();

        for (Map.Entry<Integer, Event> e: eventList.entrySet()){
        DateTime currentDate = e.getValue().getDate();
            if(currentDate.isAfter(from)&&currentDate.isBefore(to))
                filteredEvents.put(e.getKey(),e.getValue());
        }
        return filteredEvents;
    }

    public Map<Integer, Event> getNextEvents(DateTime to){
        return getForDateRange(new DateTime(), to);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, DateTime date){
        event.setAuditorium(auditorium);
        event.setDate(date);
    }

    public void removeEventByIndex(int key) {

        if(eventList.remove(index)==null)
            System.out.println("!!! Caution - remove operation failed. There are no value mapped to this key");

    }
}
