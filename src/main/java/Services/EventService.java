package Services;

import Entities.Event;

import java.util.LinkedHashMap;
import java.util.Map;

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

    public void removeEventByIndex(int key) {

        if(eventList.remove(index)==null)
            System.out.println("!!! Caution - remove operation failed. There are no value mapped to this key");

    }
}
