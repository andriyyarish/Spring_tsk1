package epamUniversity.services;


import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epamUniversity.entities.Event;
import epamUniversity.entities.Ticket;
import epamUniversity.entities.User;

import static org.hamcrest.MatcherAssert.assertThat;


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

        bookingService = context.getBean(BookingService.class);
        userService = context.getBean(UserService.class);
        userService.registerUser(user);
        date = new DateTime();
    }

    @Test
    public void testGetPriceMethod(){

        double price = bookingService.getTicketPrice(event,date,4,user);
        System.out.println(price);
    }

    @Test
    public void testGenerateTicketMethod(){
        Ticket ticket = bookingService.getTicket(event,date,4,user);
        double expectedPrice = bookingService.getTicketPrice(event,date,4,user);
        assertThat(expectedPrice, Matchers.equalTo(ticket.getPrice()));
    }

    // price for the same event and vip and regular
    @Test
    public void testGenerateTicketMethodNegative(){
        double expectedPrice = bookingService.getTicketPrice(event,date,17,user);
        Ticket ticket = bookingService.getTicket(event,date,4,user);

        assertThat("Should be greater because of vip place",expectedPrice, Matchers.greaterThan(ticket.getPrice()));
    }



}
