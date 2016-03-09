package Services;

import Entities.Event;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class EventService {
    private Map<Integer, Event> eventList = new LinkedHashMap<Integer, Event>();
    private int index = 0;

    public void addEvent(Event event){
        eventList.put(index,event);
        index ++;
    }
}
