package epamUniversity.services;

import epamUniversity.entities.User;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class UserServiceImpl implements UserService {

    private static Map<Integer,User> userList = new LinkedHashMap<Integer, User>();

    public void removeUser(Integer usrId){
        userList.remove(usrId);
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
            if (u.getFirstName().equals(name))
                usr = u;
        }
        return usr ;
    }

    public void getBookedTicket(){
//// TODO: 1/4/2017  
    }

    @Override
    public User save(@Nonnull User object) {
        userList.put(object.getId(),object);
        return object;
    }

    @Override
    public void remove(@Nonnull User object) {
        userList.remove(object.getId());
    }

    @Override
    public User getById(@Nonnull Integer id) {
        return userList.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userList.values();
    }
}
