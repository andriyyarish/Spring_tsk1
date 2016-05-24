package Entities;

import Services.EventService;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class Event {
    EventService eventService;
    public Event(String name, double price, String rating){
        this.name=name;
        this.price=price;
        this.rating=rating;
    }
    public Event(){

    }

    private String name;
    private double price;
    private String rating; //Rating rating;

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
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

    private void init(){
        eventService.addEvent(this);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating='" + rating + '\'' +
                '}';
    }

}
