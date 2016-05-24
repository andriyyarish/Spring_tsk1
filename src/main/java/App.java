import Entities.Event;
import Entities.User;
import Services.EventService;
import Services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class App {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");

    static Event event;
    static EventService service;

    static User usr1;
    static User usr2;
    static UserService uService;


    public static void main(String[] args) throws Exception {

        initEventk();
        initUser();
        //service.removeEventByIndex(3);

        System.out.println(event.toString() + "\n");
        System.out.println(service.getEventMap().toString() + "\n");
    }

    private static void initEventk(){
        event = (Event) context.getBean("event");
        service = (EventService) context.getBean("eventService");
        service.addEvent(event);
        Event event2 = event;
        event2.setName("event 2");
        service.addEvent(event2);
    }

    private static void initUser() throws Exception {
        System.out.println("... start user initialization");
        usr1 = (User) context.getBean("user1");
        usr2 = (User) context.getBean("user2");
        System.out.println("User 1 init by spring : " + usr1.toString() + "\n");
        System.out.println("User 2 init by spring : " + usr2.toString() + "\n");

        uService = (UserService) context.getBean("userService");

        uService.registerUser(usr1);
        uService.registerUser(usr2);

        System.out.println("Check that user with name Mike are in list: " + uService.getUserByName("mike").toString() + "\n");
    }
}
