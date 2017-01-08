package epamUniversity.services.discount.strategy;

import epamUniversity.entities.Event;
import epamUniversity.entities.EventInstance;
import epamUniversity.entities.User;

/**
 * Created by Andriy_Yarish on 1/7/2017.
 */
public interface DiscountStrategy {

    double execute(User user, EventInstance event);
}
