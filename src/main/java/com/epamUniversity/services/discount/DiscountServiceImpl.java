package com.epamUniversity.services.discount;

import com.epamUniversity.model.Event;
import com.epamUniversity.model.EventInstance;
import com.epamUniversity.model.User;
import com.epamUniversity.services.discount.strategy.DiscountStrategy;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class DiscountServiceImpl implements DiscountService {

    private static double SALE = 0.0;

    List<DiscountStrategy> strategies;

    public DiscountServiceImpl(List<DiscountStrategy> strategies){
        this.strategies = strategies;
    }

    public DiscountServiceImpl() {
    }

    //    @Override
//    public double getDiscount(@Nullable User user, @Nonnull EventInstance event, @Nonnull DateTime airDateTime, long numberOfTickets) {
//        double totalDiscountPercent = 0.00;
//        for (DiscountStrategy ds: strategies){
//            totalDiscountPercent += ds.execute(user, event);
//        }
//        return totalDiscountPercent;
//    }

    public static double getBasePriceMultiplier(Event event, int seat, User user){
        return getRatingMultiplier(event) +
                getSeatTypeMultiplier(event,seat) -
                getBirthdayDiscount(user) -
                SALE;
    }

    private static double getBirthdayDiscount(User user){
        double discount = 0.0;
        DateTime today = DateTime.now();
        DateTime usrBirtday = user.getDateOfBirth();

        if(today.getDayOfMonth()==usrBirtday.getDayOfMonth()
                && today.getMonthOfYear()==usrBirtday.getDayOfYear())
            discount = 0.15;

        return discount;
    }

    private static double getSeatTypeMultiplier(Event event, int seat){
//        if (event.getAuditorium().getVipSeats().contains(String.valueOf(seat)))
//            return VIPMULTIPLIER;
//        else
            return 1.0;
    }

    private static double getRatingMultiplier(Event event){
//        switch (event.getEventRating()){
//            case LOW:
//                return 0.0;
//            case MEDIUM:
//                return 0.2;
//            case HIGH:
//                return 0.5;
//            default:
//                return 0.0;
//        }
        return 1.0;
    }


    @Override
    public double getDiscount(@Nullable User user, @Nonnull EventInstance event) {
        return 0;
    }
}
