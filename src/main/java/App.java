import Entities.Event;
import Services.EventService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class App {
static Event event;
static EventService service;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        event = (Event) context.getBean("event");
        service = (EventService) context.getBean("eventService");
        service.addEvent(event);
        Event event2 = event;
        event2.setName("event 2");
        service.addEvent(event2);
        service.removeEventByIndex(3);

        System.out.println(event.toString());
        System.out.println(service.getEventMap().toString());
    }
}
