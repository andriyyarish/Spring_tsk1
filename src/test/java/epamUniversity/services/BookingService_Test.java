package epamUniversity.services;

import epamUniversity.Entities.Event;
import epamUniversity.Entities.User;
import epamUniversity.Services.BookingService;
import epamUniversity.Services.UserService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by Newman on 12/27/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingService_Test {

    User user;
    Event event;
    BookingService bookingService;
    UserService userService;
    DateTime date;

    @Before
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringUniver.xml");
        user = (User) context.getBean("usr3");
        event = (Event) context.getBean("event1");
        bookingService = new BookingService();
        userService = context.getBean(UserService.class);
        userService.registerUser(user);
        date = new DateTime();
    }

    @Test
    public void test(){

        double price = bookingService.getTicketPrice(event,date,4,user);
        System.out.println(price);
    }
}
