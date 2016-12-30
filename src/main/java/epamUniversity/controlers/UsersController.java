package epamUniversity.controlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.applet.AppletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import epamUniversity.entities.User;
import epamUniversity.services.UserService;

/**
 * Created by Andriy_Yarish on 12/28/2016.
 */

@Controller
public class UsersController implements InitializingBean {
    @Autowired
    private UserService userService;
    @Autowired
    private GsonBuilder gsonBuilder;
    private Gson gson;

    @RequestMapping (value = "/users", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllUsers(){
        Map<Integer, User> userList = userService.getUserList();
        String s = gson.toJson(userList);
        return new ModelAndView("users", "message", s);
    }

    @RequestMapping (value = "/user/{id}", method = RequestMethod.GET, produces =MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getUserById(@PathVariable(value = "id", required = true) int id){
        User userById = userService.getUserById(id);
        return new ModelAndView("users", "message", userById.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        gson = gsonBuilder.serializeNulls()
                .setPrettyPrinting()
                .disableInnerClassSerialization()
                .create();
    }
}
