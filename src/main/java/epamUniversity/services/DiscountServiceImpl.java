package epamUniversity.services;

import epamUniversity.entities.Event;
import epamUniversity.entities.User;
import org.joda.time.DateTime;

import static epamUniversity.services.BookingServiceImpl.VIPMULTIPLIER;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class DiscountServiceImpl {

    private static double SALE = 0.0;


    public DiscountServiceImpl(){

    }

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

}
