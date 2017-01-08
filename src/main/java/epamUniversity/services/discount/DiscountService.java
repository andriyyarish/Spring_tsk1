package epamUniversity.services.discount;
import epamUniversity.entities.Event;
import epamUniversity.entities.EventInstance;
import epamUniversity.entities.User;
import org.joda.time.DateTime;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


/**
 * @author Yuriy_Tkach
 */
public interface DiscountService {

    /**
     * Getting discount based on some rules for user that buys some number of
     * tickets for the specific date time of the event
     *
     * @param user
     *            User that buys tickets. Can be <code>null</code>
     * @param event
     *            Event that tickets are bought for

     * @return discount value from 0 to 100
     */
    double getDiscount(@Nullable User user, @Nonnull EventInstance event);

    //     * @param airDateTime
//     *            The date and time event will be aired
//     * @param numberOfTickets
//     *            Number of tickets that user buys

}