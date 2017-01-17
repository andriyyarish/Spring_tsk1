package epamUniversity.services;

import epamUniversity.model.EventInstance;
import org.joda.time.DateTime;

import epamUniversity.model.Auditorium;
import epamUniversity.model.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class EventServiceImpl implements EventService {

    private static Map<Integer, Event> eventList = new LinkedHashMap<Integer, Event>();
    private static Map<Event,List<EventInstance>> eventInstancesMap = new LinkedHashMap<>();
    private static int index = 0;

    @Override
    public Event save(@Nonnull Event object) {
        eventList.put(object.getId(),object);
        return object;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        Event event = null;
        for (Map.Entry<Integer,Event> e: eventList.entrySet()) {
            if (e.getValue().getName().equals(name))
                event = e.getValue();
        }
        return event;
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventList.remove(object.getId());
    }

    @Override
    public Event getById(@Nonnull Integer id) {
        return eventList.get(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventList.values();
    }

    public Map<Integer, Event> getForDateRange(DateTime from, DateTime to){
        Map<Integer, Event> filteredEvents = new HashMap<>();

        for (Map.Entry<Integer, Event> e: eventList.entrySet()){
            if(e.getValue().airsOnDates(from,to))
                filteredEvents.put(e.getKey(),e.getValue());
        }
        return filteredEvents;
    }

    public Map<Integer, Event> getNextEvents(DateTime to){
        return getForDateRange(new DateTime(), to);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, DateTime date){
        event.assignAuditorium(date,auditorium);
    }

}
