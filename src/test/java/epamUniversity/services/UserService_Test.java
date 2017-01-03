package epamUniversity.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import epamUniversity.entities.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
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
    private UserServiceImpl userServiceImpl;

    @Before
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF\\springUniver.xml");
        userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
        user = (User) context.getBean("usr1");
        user2 = (User) context.getBean("usr2");
    }

    @Test
    public void registerUser(){
        userServiceImpl.save(user);
        userServiceImpl.save(user2);
        System.out.println(userServiceImpl.getById(1));
        assertThat(userServiceImpl.getAll().size(), equalTo(2));
    }

    @Test
    public void getById(){
        userServiceImpl.save(user);
        userServiceImpl.save(user2);
        System.out.println(userServiceImpl.getById(1));
        assertThat(userServiceImpl.getById(1).getName(), equalToIgnoringCase("andriy"));
    }

    @Test
    public void checkThatInitMethodOdUserWorks(){

    }






}
