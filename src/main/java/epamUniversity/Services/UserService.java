package epamUniversity.services;

import epamUniversity.entities.User;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class UserService {

    private static Map<Integer,User> userList = new LinkedHashMap<Integer, User>();
    User user;

    public void registerUser(User usr){
        userList.put(usr.getId(),usr);
    }

    public void removeUser(Integer usrId){
        userList.remove(usrId);
    }

    public User getUserById(int Id){
        return userList.get(Id);
    }

    public User getUserByEmail(String email){
        User usr = null;
        for(User u: userList.values()) {
            if (u.getEmail() == email)
                usr = u;
        }
        return usr;
    }

    public User getUserByName(String name) throws Exception {
        User usr = null;
        for(User u: userList.values()) {
            if (u.getName().equals(name))
                usr = u;
        }
        return usr ;
    }

    public Map<Integer, User> getUserList(){
        return userList;
    }

    public void getBookedTicket(){

    }





}
