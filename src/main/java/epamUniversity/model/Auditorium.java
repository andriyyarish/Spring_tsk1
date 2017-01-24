package epamUniversity.model;

import epamUniversity.services.AuditoriumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Newman on 12/26/16.
 */
@Entity
@Table(name = "auditoriums")
public class Auditorium extends DomainObject {

    @Autowired
    private transient AuditoriumServiceImpl auditoriumService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "name")
    String name;

    @Column (name = "adress")
    String adress;

    @Column (name = "seats")
    int seats;

    transient List<String> vipSeats;

    transient Map<Integer, SeatType> allSeats;

    public Auditorium(String name, String adress, int seats, String vipSeats) {
        this.name = name;
        this.adress = adress;
        this.seats = seats;
        setVipSeats(vipSeats);
        initListOfSeats();
    }

    public Auditorium() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<String> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(String vipSeats) {
        this.vipSeats = Arrays.asList(vipSeats.split(","));
    }

    private void initListOfSeats(){
        allSeats = new HashMap<>();
        for (int i = 1; i <= seats; i++){
            if(vipSeats.contains(String.valueOf(i)))
                allSeats.put(i,SeatType.VIP);
            else
                allSeats.put(i,SeatType.REGULAR);
        }
    }

    private void init(){
        auditoriumService.setAuditorium(this);
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", seats='" + seats + '\'' +
                ", vipSeats=" + vipSeats +
                '}';
    }
}
