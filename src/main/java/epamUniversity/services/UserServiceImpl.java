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

    public void removeUser(Long usrId) {
        userRepository.delete(usrId);
    }

    public User getUserByEmail(String email) {
        User usr = null;
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(email))
                usr = u;
        }
        return usr;
    }

    public User getUserByName(String name) throws Exception {
        User usr = null;
        for (User u : userRepository.findAll()) {
            if (u.getFirstName().equals(name))
                usr = u;
        }
        return usr;
    }

    public void getBookedTicket() {
//// TODO: 1/4/2017  
    }

    @Transactional
    @Override
    public User save(@Nonnull User object) {
//        userList.put(object.getId(), object);
        User user = userRepository.findByEmail(object.getEmail());
        Account account ;
        if (user == null) {
            String encodedPass = encoder.encode(object.getPassword());
            object.setPassword(encodedPass);
            account = new Account(object);
            accountRepository.save(account);
            Account a = accountRepository.findByUsername(object.getEmail());

            object.setAccount(a);
            userRepository.save(object);
            return object;
        } else {
            throw new IllegalArgumentException("User with defined email already registered");
        }
    }

    @Override
    public void remove(@Nonnull User object) {
        //userRepository.delete(object);
        userRepository.delete(new Long(object.getId()));
        //userList.remove(object.getId());
    }

    @Override
    public User getById(@Nonnull Integer id) {
        return userRepository.findOne(new Long(id));
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
        //return userList.values();
    }
}
