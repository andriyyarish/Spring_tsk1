package com.epamUniversity.services.discount.strategy;

import com.epamUniversity.model.EventInstance;
import com.epamUniversity.model.User;

/**
 * Created by Andriy_Yarish on 1/7/2017.
 */
public interface DiscountStrategy {

    double execute(User user, EventInstance event);
}
