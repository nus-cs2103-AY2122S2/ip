package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class DateHelper {
    Date formattedDatetime;
    static final String INVALID_DATE = "Oops, please put a valid time format!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this duke.command";

    public DateHelper(String time) throws DukeException {
        try {
            String pattern = "dd/MM/yyyy";
            DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setLenient(false);
            Date date = simpleDateFormat.parse(time);
            formattedDatetime = date;
        } catch (ParseException e) {
           try {
                String pattern = "dd MMMM yyyy";
                DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                simpleDateFormat.setLenient(false);
                Date date = simpleDateFormat.parse(time);
                formattedDatetime = date;
            } catch (ParseException err) {
               throw new DukeException(INVALID_DATE);
           }
        }
    }

    public String getDatetime() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setLenient(false);
        String strDate = dateFormat.format(formattedDatetime);
        return strDate;
    }
}