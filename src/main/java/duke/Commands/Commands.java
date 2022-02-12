package duke.Commands;

import duke.DukeException;
import duke.DukeHistory;
import duke.DukeUi;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a class that contains the logic for all keywords that Duke recognizes.
 */
public abstract class Commands {

    DukeHistory history;
    String[] userInput;
    DukeUi ui;

    public Commands(DukeHistory history, String[] userInput, DukeUi ui) {
        this.history = history;
        this.userInput = userInput;
        this.ui = ui;
    }

    public abstract String validateAndExecute();

    public abstract void validate();

    public abstract String execute() throws DukeException;



    /**
     * A method that takes in a String, checks it against 2 accepted date formats and converts it into
     * a default date format for output. If the String satisfies neither format, it is simply outputted as is.
     *
     * @param date A String input that represents a date.
     * @return Either a default formatted date or the user input.
     */
    public String convertToDukeDate(String date) {
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        df1.setLenient(false);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        df2.setLenient(false);
        try {
            Date dummyDate1 = df1.parse(date);
            LocalDate dukeDate1 = dummyDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return dukeDate1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (ParseException e1) {
            try {
                Date dummyDate2 = df2.parse(date);
                LocalDate dukeDate2 = LocalDate.parse(date);
                return dukeDate2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (ParseException e2) {
                return date;
            }
        }
    }

    /**
     * A method that takes in a String representing a time period and checks if it's told in a 12-hour clock.
     * If not, the method attempts to convert it into a 12-hour clock.
     *
     * @param time A String input that represents a time period.
     * @return Either a default 12-hour clock format or the user input.
     */

    public String convertToDukeTime(String time) {
        if (time.contains("am") || time.contains("pm")
                || time.contains("AM") || time.contains("PM")
                || time.contains("Am") || time.contains("Pm")) {
            return time;
        } else {
            String splicedTime = time.substring(0, 4);
            DateFormat inputFormat = new SimpleDateFormat("HHmm");
            DateFormat outputFormat = new SimpleDateFormat("hh:mmaa");
            try {
                Date dukeTime = inputFormat.parse(splicedTime);
                return outputFormat.format(dukeTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return time;
            }
        }
    }



}
