package com.epamUniversity.services;


import com.epamUniversity.model.*;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Newman on 12/27/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingService_Test {

    User user;
    Event event;
    EventInstance eventInstance;
    BookingServiceImpl bookingService;
    UserServiceImpl userServiceImpl;
    DateTime date;

    @Before
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringUniver.xml");
        user = (User) context.getBean("usr3");
        event = (Event) context.getBean("event1");
        eventInstance = new EventInstance(event,new Auditorium(),new DateTime() );
        eventInstance.setEventParent(event);

        bookingService = context.getBean(BookingServiceImpl.class);
        userServiceImpl = context.getBean(UserServiceImpl.class);
        userServiceImpl.save(user);
        date = new DateTime();
    }

    @Test
    public void testGetPriceMethod(){

        double price = bookingService.getTicketPrice(eventInstance,date,4,user);
        System.out.println(price);
    }

    @Test
    public void testGenerateTicketMethod(){
        Ticket ticket = bookingService.getTicket(eventInstance,date,4,user);
        double expectedPrice = bookingService.getTicketPrice(eventInstance,date,4,user);
        assertThat(expectedPrice, Matchers.equalTo(ticket.getPrice()));
    }

    // price for the same event and vip and regular
    @Test
    public void testGenerateTicketMethodNegative(){
        double expectedPrice = bookingService.getTicketPrice(eventInstance,date,17,user);
        Ticket ticket = bookingService.getTicket(eventInstance,date,4,user);

        assertThat("Should be greater because of vip place",expectedPrice, Matchers.greaterThan(ticket.getPrice()));
    }



}
