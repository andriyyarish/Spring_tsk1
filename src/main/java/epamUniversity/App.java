package epamUniversity;

import epamUniversity.entities.Event;
import epamUniversity.services.EventService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class App {
static Event event;
static EventService service;
    public static void main(String[] args) throws Exception, BeansException {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringUniver.xml");

    }
}
