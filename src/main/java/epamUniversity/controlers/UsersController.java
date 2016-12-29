package epamUniversity.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import epamUniversity.entities.User;
import epamUniversity.services.UserService;

/**
 * Created by Andriy_Yarish on 12/28/2016.
 */

@Controller
public class UsersController {
    @Autowired
    UserService userService;

    @RequestMapping (value = "/users",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllUsers(){
        Map<Integer, User> userList = userService.getUserList();
        return new ModelAndView("users", "message", userList.toString());
    }
}
