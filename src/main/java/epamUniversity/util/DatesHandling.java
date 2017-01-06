package epamUniversity.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Andriy_Yarish on 1/6/2017.
 */
public class DatesHandling {

    public static DateTime parseStringToDate(String yyyyMMdd){
        DateTime dateTime;
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-mm-dd");
        dateTime = df.parseDateTime(yyyyMMdd);
        return dateTime;
    }
}
