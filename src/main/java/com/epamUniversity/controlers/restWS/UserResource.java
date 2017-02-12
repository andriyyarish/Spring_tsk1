package com.epamUniversity.controlers.restWS;

import com.epamUniversity.controlers.restWS.dto.RefillRequest;
import com.epamUniversity.model.User;
import com.epamUniversity.services.AccountService;
import com.epamUniversity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static java.lang.String.valueOf;

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

    @Autowired
    AccountService accountService;

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

    @RequestMapping(value = "/refill", method = RequestMethod.POST)
    public ResponseEntity<String> refillBalance(@RequestBody RefillRequest request){
        User user = userService.getById(request.getUserId());
        ResponseEntity<String> responseEntity ;
        if(user != null) {
            accountService.refillBalance(user, request.getAmmount());
            responseEntity = new ResponseEntity<String>("User: "+user.getEmail() + " refilled with : " +  valueOf(request.getAmmount())
                    , HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<String>("No user with id: " + request.getUserId() ,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
