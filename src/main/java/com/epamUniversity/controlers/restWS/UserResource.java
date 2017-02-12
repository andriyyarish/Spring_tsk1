package com.epamUniversity.controlers.restWS;

import com.epamUniversity.model.User;
import com.epamUniversity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Andriy_Yarish on 2/11/2017.
 */
@RestController
@RequestMapping(value = {"/rest/users"},
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    @Autowired
    UserService userService;

    @RequestMapping
    public @ResponseBody Collection<User> getAll(){
        Collection<User> userCollection = userService.getAll();
        return userCollection;
    }

    @RequestMapping(value = "/{userId}")
    public @ResponseBody User getById(@PathVariable(value = "userId") Long userId){
        User user = userService.getById(userId);
        return user;
    }

}
