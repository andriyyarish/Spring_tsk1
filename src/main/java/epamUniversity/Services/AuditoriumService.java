package epamUniversity.Services;

import epamUniversity.Entities.Auditorium;

import java.util.LinkedList;
import java.util.List;

/**
 * AuditoriumService - Returns info about auditoriums and places

 Since auditorium information is usually static, store it in some property file. The information that needs to be stored is:
 name
 number of seats
 vip seats (comma-separated list of expensive seats)
 Several auditoriums can be stored in separate property files, information from them could be injected into the AuditoriumService

 getAuditoriums(), getSeatsNumber(), getVipSeats()
 */
public class AuditoriumService {
    List<Auditorium> auditoriumList;

    public AuditoriumService(){
        auditoriumList = new LinkedList<>();
    }

    public List<Auditorium> getAuditoriums(){
        return auditoriumList;
    }

    public Auditorium getAuditorium(String name){
        return auditoriumList.stream()
                .filter(a -> a.getName().contains(name))
                .findFirst()
                .get();
    }

    public void setAuditorium(Auditorium auditorium){
        auditoriumList.add(auditorium);
    }



}
