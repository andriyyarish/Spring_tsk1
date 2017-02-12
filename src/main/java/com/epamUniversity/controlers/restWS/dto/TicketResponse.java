package com.epamUniversity.controlers.restWS.dto;

import com.epamUniversity.model.Ticket;

import java.io.Serializable;

/**
 * Created by Andriy_Yarish on 2/12/2017.
 */
public class TicketResponse implements Serializable {

    private String owner;
    private String event;
    private String auditorium;
    private String seat;
    private String price;

    public TicketResponse(Ticket ticket) {
        this.owner = ticket.getUser().getEmail();
        this.event = ticket.getEvent().getEventParent().getName();
        this.auditorium = ticket.getEvent().getAuditorium().getName();
        this.seat = String.valueOf(ticket.getSeat());
        this.price = String.valueOf(ticket.getPrice());
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(String auditorium) {
        this.auditorium = auditorium;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
