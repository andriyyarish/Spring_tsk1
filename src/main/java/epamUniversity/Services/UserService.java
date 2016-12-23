package epamUniversity.Services;

import epamUniversity.Entities.User;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class UserService {
    private  Map<Integer,User> userList = new LinkedHashMap<Integer, User>();
    User user;

    public void registerUser(){

    }

    public void removeUser(){

    }

    public User getUserById(int ID){

        return new User();
    }

    public User getUserByEmail(String email){

        return new User();
    }

    public User getUserByName(String name){

        return new User();
    }

    public void getBookedTicket(){

    }





}
