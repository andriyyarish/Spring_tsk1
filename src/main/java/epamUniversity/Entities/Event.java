package epamUniversity.Entities;

import epamUniversity.Services.EventService;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class Event {

    private EventService eventService;
    private Auditorium auditorium;
    private String name;
    private double price;
    private String rating;
    private Date date;

    public Event(String name, double price, String rating){
        this();
        this.name=name;
        this.price=price;
        this.rating=rating;

    }

    public Event(){
        this.date = new Date();
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setAuditorium(Auditorium auditorium){
        this.auditorium = auditorium;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public Date getDate(){
        return date;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    private void init(){
        eventService.addEvent(this);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name = '" + name + '\'' +
                ", price = " + price +
                ", rating = '" + rating + '\'' +
                ", date = '"+ date.toString() + '\''+
                '}';
    }

}
