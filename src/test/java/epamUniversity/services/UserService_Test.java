package epamUniversity.services;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import epamUniversity.Entities.User;
import epamUniversity.Services.UserService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;

/**
 * Created by Andriy_Yarish on 12/27/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration()

public class UserService_Test {

    @Autowired
    private User user;
    @Autowired
    private User user2;
    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringUniver.xml");
        userService = (UserService) context.getBean("userService");
        user = (User) context.getBean("usr1");
        user2 = (User) context.getBean("usr2");
    }

    @Test
    public void registerUser(){
        userService.registerUser(user);
        userService.registerUser(user2);
        System.out.println(userService.getUserById(1));
        assertThat(userService.getUserList().size(), equalTo(2));
    }

    @Test
    public void getById(){
        //userService.registerUser(user);
       // userService.registerUser(user2);
        System.out.println(userService.getUserById(1));
        assertThat(userService.getUserById(1).getName(), equalToIgnoringCase("andriy"));
    }






}
