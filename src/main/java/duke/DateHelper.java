package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 * DateHelper is used to help convert Strings into the proper date time formats.
 * It also helps to check for the validity of the date inputted.
 * If the date input is invalid, (e.g. missing the day, short form months), it throws a DukeException.
 */
public class DateHelper {
    private Date formattedDatetime;
    private static final String INVALID_DATE = "Oops, please put a valid time format!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this duke.command";

    /**
     * Constructs a DateHelper.
     *
     * @param time Time input is based on the datetime provided by the user.
     * @throws DukeException Thrown when there is an invalid datetime format.
     */
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

    /**
     * Returns the formatted date time based on the user's input.
     *
     * @return Returns a formatted date time (i.e. dd MMMM yyyy).
     */
    public String getDatetime() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setLenient(false);
        String strDate = dateFormat.format(formattedDatetime);
        return strDate;
    }
}
