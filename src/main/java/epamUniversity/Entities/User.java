package epamUniversity.Entities;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import epamUniversity.Services.UserService;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class User {

    @Autowired
    private UserService userService;
    private static int index;
    private int id;
    private String email;
    private String name;
    private Date dateOfBirth;

    public User (String name, String email){
        id = ++index;
        this.name = name;
        this.email = email;
    }

    public User (){

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
