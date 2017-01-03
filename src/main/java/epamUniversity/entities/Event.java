package epamUniversity.entities;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;


import epamUniversity.services.EventServiceImpl;

import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class Event extends DomainObject {
    private static int index ;

    @Autowired
    private EventServiceImpl eventService;

    private String name;

    private NavigableSet<DateTime> airDates = new TreeSet<>();

    private double basePrice;

    private Rating eventRating;

    private DateTime date;

    private NavigableMap<DateTime, Auditorium> auditoriums;

    public Event(String name, double price, String rating, DateTime date) {
        this.name = name;
        this.basePrice = price;
        this.eventRating = Rating.valueOf(rating);
        this.date = date;
        super.setId(index++);
    }

    /**
     * Checks if event is aired on particular <code>dateTime</code> and assigns
     * auditorium to it.
     *
     * @param dateTime   Date and time of aired event for which to assign
     * @param auditorium Auditorium that should be assigned
     * @return <code>true</code> if successful, <code>false</code> if event is
     * not aired on that date
     */
    public boolean assignAuditorium(DateTime dateTime, Auditorium auditorium) {
        if (airDates.contains(dateTime)) {
            auditoriums.put(dateTime, auditorium);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes auditorium assignment from event
     *
     * @param dateTime Date and time to remove auditorium for
     * @return <code>true</code> if successful, <code>false</code> if not
     * removed
     */
    public boolean removeAuditoriumAssignment(DateTime dateTime) {
        return auditoriums.remove(dateTime) != null;
    }

    /**
     * Add date and time of event air
     *
     * @param dateTime Date and time to add
     * @return <code>true</code> if successful, <code>false</code> if already
     * there
     */
    public boolean addAirDateTime(DateTime dateTime) {
        return airDates.add(dateTime);
    }

    /**
     * Adding date and time of event air and assigning auditorium to that
     *
     * @param dateTime   Date and time to add
     * @param auditorium Auditorium to add if success in date time add
     * @return <code>true</code> if successful, <code>false</code> if already
     * there
     */
    public boolean addAirDateTime(DateTime dateTime, Auditorium auditorium) {
        boolean result = airDates.add(dateTime);
        if (result) {
            auditoriums.put(dateTime, auditorium);
        }
        return result;
    }

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     *
     * @param dateTime Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    public boolean removeAirDateTime(DateTime dateTime) {
        boolean result = airDates.remove(dateTime);
        if (result) {
            auditoriums.remove(dateTime);
        }
        return result;
    }

    /**
     * Checks if event airs on particular date and time
     *
     * @param dateTime
     *            Date and time to check
     * @return <code>true</code> event airs on that date and time
     */
    public boolean airsOnDateTime(DateTime dateTime) {
        return airDates.stream().anyMatch(dt -> dt.equals(dateTime));
    }

    /**
     * Checks if event airs on particular date
     *
     * @param date
     *            Date to ckeck
     * @return <code>true</code> event airs on that date
     */
    public boolean airsOnDate(DateTime date) {
        return airDates.stream().anyMatch(dt -> dt.toLocalDate().equals(date));
    }

    /**
     * Checking if event airs on dates between <code>from</code> and
     * <code>to</code> inclusive
     *
     * @param from
     *            Start date to check
     * @param to
     *            End date to check
     * @return <code>true</code> event airs on dates
     */
    public boolean airsOnDates(DateTime from, DateTime to) {
        return airDates.stream()
                .anyMatch(dt -> dt.isBefore(from) && dt.isAfter(to));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NavigableSet<DateTime> getAirDates() {
        return airDates;
    }

    public void setAirDates(NavigableSet<DateTime> airDates) {
        this.airDates = airDates;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public Rating getRating() {
        return eventRating;
    }

    public void setRating(Rating rating) {
        this.eventRating = rating;
    }

    public NavigableMap<DateTime, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(NavigableMap<DateTime, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    private void init() {
        eventService.save(this);
    }


}
