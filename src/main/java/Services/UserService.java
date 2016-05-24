package Services;

import Entities.User;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class UserService {

    private  Map<Integer,User> userList = new LinkedHashMap<Integer, User>();
    User user;


    public void registerUser(User u){
        userList.put(u.getId(),u);
    }

    public void removeUser(Integer key){
        userList.remove(key);
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
        User usr;
        for(User u: userList.values()) {
            if (u.getName().equals(name))
                return u;
        }
        return new User();
    }

    public void getBookedTicket(){

    }





}
