import Entities.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class App {
static Event event;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        event = (Event) context.getBean("event");
        System.out.println(event.toString());
    }
}
