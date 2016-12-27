package epamUniversity.services;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import epamUniversity.Entities.Auditorium;
import epamUniversity.Entities.Event;
import epamUniversity.Services.EventService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by Andriy_Yarish on 12/27/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration()

public class EventService_Test {

    @Autowired()
    @Qualifier("eventService")
    private EventService eventService;
    private Event event1;
    private Event event2;
    private Event event3;
    private Auditorium auditorium;
    private ApplicationContext applicationContext;

    @Before
    public void setUp(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringUniver.xml");
        eventService = applicationContext.getBean(EventService.class);
        event1 = (Event) applicationContext.getBean("event1");
        event2 = (Event) applicationContext.getBean("event2");
        event3 = (Event) applicationContext.getBean("event2");
        auditorium = applicationContext.getBean(Auditorium.class);
    }

    @Test
    public void addEvent(){
        eventService.addEvent(event1);
        eventService.addEvent(event2);
        assertThat(eventService.getEventMap().size(), Matchers.equalTo(2));
    }

    @Test
    public void getEvent(){
        Event eventById = eventService.getEventById(0);
        assertThat(eventById, Matchers.notNullValue());
    }

    @Test
    public void assignAuditorium(){
        int id = eventService.addEvent(event3);
        eventService.assignAuditorium(event3,auditorium,new DateTime());
        assertThat(eventService.getEventById(id).getAuditorium(), Matchers.notNullValue());
    }

    @Test
    public void getForDateRange(){
        DateTime from = new DateTime().minusDays(10);
        DateTime to = new DateTime().plusDays(10);


        Map<Integer, Event> forDateRange = eventService.getForDateRange(from,to);
        assertThat(forDateRange.size(), greaterThanOrEqualTo(1));
    }

}
