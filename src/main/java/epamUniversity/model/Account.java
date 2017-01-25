package epamUniversity.model;
import epamUniversity.model.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a UserAccount.
 *
 */

@Entity
@Table(name = "accounts")
public class Account extends DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "ballance")
    private double ballance;

    @ManyToMany
    @JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Column (name = "user_id")
//    private Long user;

    public Account (User user){
        this();
        this.username = user.getEmail();
        this.password = user.getPassword();
//        this.user = user.getId();


    }

    public Account(){
        this.ballance = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public double getBallance() {
        return ballance;
    }

    public void setBallance(double ballance) {
        this.ballance = ballance;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}