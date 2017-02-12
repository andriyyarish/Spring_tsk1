package com.epamUniversity.controlers.restWS.dto;

import java.io.Serializable;

/**
 * Created by Andriy_Yarish on 2/12/2017.
 */
public class RefillRequest implements Serializable {
    private Long userId;
    private double ammount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }
}
