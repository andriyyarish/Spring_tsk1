package epamUniversity.services.discount.strategy;

import epamUniversity.model.EventInstance;
import epamUniversity.model.User;

/**
 * Returns percentage of discount currently 50 %
 */
public class TenthTicketDiscountStrategy implements DiscountStrategy {
    private double TENTICKET_DISCOUNT = 50.0;

    @Override
    public double execute(User user, EventInstance event) {
        int size = user.getTickets().size();
        if (size % 10 == 0 && size > 0)
            return TENTICKET_DISCOUNT;
        else return 0;
    }
}
