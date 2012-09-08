package my.gambi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Victor Machado
 */
public class DateUtils {


    private static String[] patterns = {"yyyy-MM-dd", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S"};

    public static Date parse(String dateString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateString);
    }

    public static Date parse(String dateString) throws ParseException {
        String[] parts = dateString.split("[-/.: ]+");
        if (parts.length < 3) {
            throw new IllegalArgumentException(
                    String.format(
                    "Date String \"%s\" is not in a supported format for Date parsing",
                    dateString));
        }
        String format = getFormat(parts);
        String formattedDateString = getFormattedDateString(parts);
        return parse(formattedDateString, format);
    }

    private static String getFormat(String[] parts) {
        return patterns[parts.length - 3];
    }

    private static String getFormattedDateString(String[] parts) {
        
        StringBuilder dateStringBuilder = new StringBuilder();
        dateStringBuilder.append(parts[0]);
        dateStringBuilder.append("-");
        dateStringBuilder.append(parts[1]);
        dateStringBuilder.append("-");
        dateStringBuilder.append(parts[2]);
        if (parts.length > 3) {
            dateStringBuilder.append(" ");
            dateStringBuilder.append(parts[3]);
            if (parts.length > 4) {
                dateStringBuilder.append(":");
                dateStringBuilder.append(parts[4]);
                if (parts.length > 5) {
                    dateStringBuilder.append(":");
                    dateStringBuilder.append(parts[5]);
                    if (parts.length > 6) {
                        dateStringBuilder.append(".");
                        dateStringBuilder.append(parts[6]);
                    }
                }
            }
        }
        return dateStringBuilder.toString();
    }
}
