package epamUniversity.model;

import epamUniversity.services.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class User extends DomainObject {

    private static int index;
    @Autowired
    private transient UserService userService;

    private String firstName;

    private String lastName;

    private String email;

//    @JsonDeserialize(using=LocalDateDeserializer.class)
    private DateTime dateOfBirth;

    private NavigableSet<Ticket> tickets = new TreeSet<>();

    private String password;

    private String role;

    public User(String firstName, String lastName, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(){
        int y = new Random().nextInt(50);
        int d = new Random().nextInt(10);
        dateOfBirth = new DateTime().minusYears(y).minusDays(d);
        super.setId(index++);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets.addAll(tickets);
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void init(){
        userService.save(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }
}
