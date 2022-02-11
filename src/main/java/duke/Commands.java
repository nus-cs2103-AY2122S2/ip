package duke;

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

    /**
     * A method that, when called, attempts to initialize an Event task using the provided String[] tokens
     * to build a description, date and time.
     *
     * It then gets the inputted instance of DukeHistory to add an Event task entry into it's records
     * using the generated description, date and time.
     *
     * @param tokens A String[] of tokens inputted by the user.
     * @param history An instance of DukeHistory.
     * @throws DukeException If the '/at' phrase is not detected in the String[] of tokens.
     * @return Event_task response.
     */
    public String event(String[] tokens, DukeHistory history) throws DukeException {
        String description = "";
        String date = "";
        String time = "";
        int timeStart = -1; // -1 is a placeholder to indicate /at has not been found
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                timeStart = i;
                break;
            } else {
                description = description.concat(tokens[i]);
            }
            description = description.concat(" ");
        }

        // Check for timeFrame
        if (timeStart == -1 || tokens.length - timeStart < 3) {
            throw new DukeException("'/at' not detected");
        }
        // Handle Date
        date = date.concat(convertToDukeDate(tokens[timeStart + 1]));
        // Handle Time
        String[] arr = tokens[timeStart + 2].split("-");
        time = time.concat(convertToDukeTime(arr[0])
                .concat("-")
                .concat(convertToDukeTime(arr[1])));
        return history.addEvent(description, date, time);
    }

    /**
     * A method that, when called, checks the validity of a given entry index before getting the inputted instance
     * of DukeHistory to delete that entry in its records.
     *  @param index An integer indicating the desired entry to delete.
     * @param history An instance of DukeHistory
     * @return Delete response.
     */
    public String delete(int index, DukeHistory history) {
        if (index < 0 || index > history.getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return history.deleteTask(index);
    }

    /**
     * A method that, when called, builds a String phrase with the inputted String[] tokens before getting the
     * inputted instance of DukeHistory to call findPhrase().
     *
     * It then prints the returned String for the user to see.
     *
     * @param tokens A String[] of tokens inputted by the user.
     * @param history An instance of DukeHistory.
     * @throws DukeException If no task is found matching the inputted phrase.
     * @return Find response.
     */
    public String find(String[] tokens, DukeHistory history) throws DukeException {
        StringBuilder phrase = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            phrase.append(tokens[i]);
            if (i != (tokens.length - 1)) {
                phrase.append(" ");
            }
        }
        String border = "_______________________________________________________\n";
        return border
                + "Here is what we found:\n"
                + history.findPhrase(phrase.toString()) + border;
    }
}
