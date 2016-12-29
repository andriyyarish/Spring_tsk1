package epamUniversity.Entities;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;



import epamUniversity.Services.EventService;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class Event {
    @Autowired
    private EventService eventService;
    @Autowired
    private Auditorium auditorium;
    private String name;
    private double price;
    private Rating rating;
    private DateTime date;

    public Event(String name, double price, String rating, DateTime date) {
        this.name = name;
        this.price = price;
        this.rating = Rating.valueOf(rating);
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = Rating.valueOf(rating);
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    private void init() {
        eventService.addEvent(this);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name = '" + name + '\'' +
                ", price = " + price +
                ", rating = '" + rating + '\'' +
                ", date = '" + date.toString() + '\'' +
                '}';
    }

}
