package epamUniversity.services;

import epamUniversity.dao.AccountRepository;
import epamUniversity.dao.UserRepository;
import epamUniversity.model.Account;
import epamUniversity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    private static Map<Integer,User> userList = new LinkedHashMap<Integer, User>();

    public void removeUser(Integer usrId){
        userList.remove(usrId);
    }

    public User getUserByEmail(String email){
        User usr = null;
        for(User u: userList.values()) {
            if (u.getEmail().equals(email))
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

    @Transactional
    @Override
    public User save(@Nonnull User object) {
        userList.put(object.getId(),object);
        User user = userRepository.findByEmail(object.getEmail());
        if(user==null) {
            String encodedPass = encoder.encode(object.getPassword());
            object.setPassword(encodedPass);
            userRepository.save(object);
            accountRepository.save(new Account(object));
            return object;
        } else{
            throw new IllegalArgumentException("User with defined email already registered");
        }
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
