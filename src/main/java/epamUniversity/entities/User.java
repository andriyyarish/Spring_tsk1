package epamUniversity.entities;

import epamUniversity.services.UserServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class User extends DomainObject {

    private static int index;
    @Autowired
    private transient UserServiceImpl userServiceImpl;
    private transient DateTime dateOfBirth;
    private String email;
    private String name;
    private List<Ticket> bookingHistory;

    public User (String name, String email){
        this();
        this.name = name;
        this.email = email;
    }

    public User (){
        bookingHistory = new LinkedList<>();
        int y = new Random().nextInt(20) + 10;
        dateOfBirth = new DateTime().minusYears(y);
        super.setId(index++);
    }

    public UserServiceImpl getUserServiceImpl() {
        return userServiceImpl;
    }

    public User setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        return this;
    }

    public DateTime getDateOfBirth(){
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public void setDateOfBirth(DateTime dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public List<Ticket> getBookingHistory(){
        return bookingHistory;
    }

    public void addToBookingHistory(Ticket ticket){
        bookingHistory.add(ticket);
    }

    public void init(){
        userServiceImpl.save(this);
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + super.getId() +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bookingHistory=" + bookingHistory +
                '}';
    }
}
