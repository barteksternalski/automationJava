package helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Helper {

    public static String getGeneratedStringWithLength(int length) {
        String s = UUID.randomUUID().toString().replace("-", "_");
        length = length <= s.length() ? length : s.length();

        return s.substring(0, length);
    }

    public static String getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

}
