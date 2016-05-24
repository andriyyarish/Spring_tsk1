package Entities;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class User {
    private static int index;
    private int id;
    private  String email;
    private  String name;

    public User (String name, String email){
        id = ++index;
        this.name = name;
        this.email = email;
    }
    public User (){

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
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
