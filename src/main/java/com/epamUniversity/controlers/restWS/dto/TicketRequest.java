package com.epamUniversity.controlers.restWS.dto;

import java.io.Serializable;

/**
 * Created by Andriy_Yarish on 2/12/2017.
 */
public class TicketRequest implements Serializable {
    private Long eventId;
    private Long userId;
    private Long seat;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }
}
