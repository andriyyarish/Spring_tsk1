package epamUniversity.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Collection;

/**
 * Created by Andriy_Yarish on 1/6/2017.
 */
public class DatesHandling {

    public static DateTime parseStringToDate(String yyyyMMdd) {
        DateTime dateTime;
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-mm-dd");
        dateTime = df.parseDateTime(yyyyMMdd);
        return dateTime;
    }

    /**
     * Day only instance comparator is used to check that dates are equal
     * @param c colection of DateTime
     * @param d DateTime instance
     * @return
     */
    public static boolean containsDate(Collection<DateTime> c, DateTime d) {
        boolean contains = false;
        DateTimeComparator comparator = DateTimeComparator.getDateOnlyInstance();
        for (DateTime dt : c)
            if (comparator.compare(dt, d) == 0)
                contains = true;
        return contains;


    }
}
