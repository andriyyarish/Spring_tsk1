package epamUniversity.services.discount.strategy;

import epamUniversity.model.EventInstance;
import epamUniversity.model.User;
import org.joda.time.DateTime;

/**
 * Created by Andriy_Yarish on 1/7/2017.
 */
public class BirthdayDiscountStrategy implements DiscountStrategy {
    private double BIRTHDAY_DISCOUNT = 5.00;

    @Override
    public double execute(User user, EventInstance event) {
        DateTime bd ;
        DateTime ed ;
        try {
            bd = user.getDateOfBirth();
            ed = event.getDateTime();

            if(bd.getDayOfMonth()==ed.getDayOfMonth()&& bd.getMonthOfYear()==ed.getMonthOfYear())
                return BIRTHDAY_DISCOUNT;
            else
                return 0.0;

        }catch (NullPointerException npe){
            return 0.0;
            //TODO Need to log something or throw exception
        }


    }
}
