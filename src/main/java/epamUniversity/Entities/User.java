package epamUniversity.entities;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import epamUniversity.services.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class User {

    private static int index;
    @Autowired
    private transient UserService userService;
    private transient DateTime dateOfBirth;
    private int id;
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
        id = index++;
    }

    public UserService getUserService() {
        return userService;
    }

    public User setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        User.index = index;
    }

    public int getId() {
        return id;
    }

    public DateTime getDateOfBirth(){
        return dateOfBirth;
    }

    public User setId(int id) {
        this.id = id;
        return this;
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
        userService.registerUser(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
