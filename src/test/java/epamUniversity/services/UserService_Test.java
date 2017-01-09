package epamUniversity.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import epamUniversity.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Andriy_Yarish on 12/27/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration()

public class UserService_Test {

    private UserService userService;

    private User user;

    @Before
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("springUniver.xml");
        user = (User) context.getBean("newusr");
        userService = context.getBean(UserService.class);
        user.setEmail("test");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        System.out.println(gson.toJson(user));
    }

    @Test
    public void registerUserAndCheckThatIdAssigned(){
        User u = userService.save(user);
        assertEquals(userService.getById(u.getId()).getEmail(),"test");
    }

    @Test
    public void getById(){
        userService.save(user);
        System.out.println(userService.getById(1));
        assertThat(userService.getById(1).getFirstName(), equalToIgnoringCase("Andriy"));
    }

    @Test
    public void getAllUsers(){
        Collection<User> cu = userService.getAll();

        Assert.assertTrue(cu.size()>0);
    }

    @Test
    public void getEmailsViaLiambdas(){
        List<String> uls = userService.getAll()
                .stream()
                .map(user -> user.getEmail())
                .collect(Collectors.toList());
        System.out.println(uls);
    }






}
