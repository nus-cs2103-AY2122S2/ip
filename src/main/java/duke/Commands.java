package duke;

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
public class Commands {

    public Commands() { // Empty Constructor
    }

    /**
     *  Prints a response to "bye" command to indicate that the command was accepted.
     */
    public void bye() { // Get DukeLCH to Exit
        String bye = "_______________________________________________________\n"
                + "Goodbye. I hope to be of service to you again soon!\n"
                + "_______________________________________________________\n";
        System.out.println(bye);
    }

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
     * A method that, when called, gets the inputted instance of DukeHistory
     * to print a list of all tasks in its records.
     *
     * @param history An instance of DukeHistory.
     */
    public void list(DukeHistory history) { // Get DukeLCH to List cmdHistory
        String border = "_______________________________________________________\n";
        System.out.println(border
                + "These are your tasks that we have in our records:\n"
                + history.printAll() + border);
    }

    /**
     * A method that, when called, checks the validity of a given entry index before getting the inputted instance
     * of DukeHistory to mark that entry in its records as done.
     *
     * @param index An integer indicating the desired entry to mark.
     * @param history An instance of DukeHistory.
     */
    public void mark(int index, DukeHistory history) {
        if (index < 0 || index > history.getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
        history.getTask(index).isMarked();
        Task currTask = history.getTask(index);
        String tasking = "";
        if (currTask instanceof ToDos) {
            ToDos temp = (ToDos) currTask;
            tasking = tasking.concat(temp.getToDo());
        } else if (currTask instanceof Deadlines) {
            Deadlines temp = (Deadlines) currTask;
            tasking = tasking.concat(temp.getDeadline());
        } else if (currTask instanceof Event) {
            Event temp = (Event) currTask;
            tasking = tasking.concat(temp.getEvent());
        } else {
            System.out.println("Error occurred while processing " + currTask.getTask()); // Temporary error handler
        }
        String msg = "_______________________________________________________\n"
                + "Well done! You have completed the task:\n"
                + "    " + tasking
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    /**
     * A method that, when called, checks the validity of a given entry index before getting the inputted instance
     * of DukeHistory to unmark that entry in its records.
     *
     * @param index An integer indicating the desired entry to unmark.
     * @param history An instance of DukeHistory.
     */
    public void unmark(int index, DukeHistory history) {
        if (index < 0 || index > history.getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
        history.getTask(index).isUnmarked();
        Task currTask = history.getTask(index);
        String tasking = "";
        if (currTask instanceof ToDos) {
            ToDos temp = (ToDos) currTask;
            tasking = tasking.concat(temp.getToDo());
        } else if (currTask instanceof Deadlines) {
            Deadlines temp = (Deadlines) currTask;
            tasking = tasking.concat(temp.getDeadline());
        } else if (currTask instanceof Event) {
            Event temp = (Event) currTask;
            tasking = tasking.concat(temp.getEvent());
        } else {
            System.out.println("Error occurred while processing " + currTask.getTask()); // Temporary error handler
        }
        String msg = "_______________________________________________________\n"
                + "A reminder that the following task has not been done:\n"
                + "    " + tasking
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    /**
     * A method that, when called, uses the provided String[] tokens to build a description for a ToDos Task.
     *
     * It then gets the inputted instance of DukeHistory to add a ToDos task entry into it's record
     * using the generated description.
     *
     * @param tokens A String[] of tokens inputted by the user.
     * @param history An instance of DukeHistory.
     */
    public void todo(String[] tokens, DukeHistory history) {
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            description = description.concat(tokens[i]);
            if (i != (tokens.length - 1)) {
                description = description.concat(" ");
            }
        }
        history.addToDo(description);
    }

    /**
     * A method that, when called, uses the provided String[] tokens to build a description, date and time
     * for a Deadline Task.
     *
     * It then gets the inputted instance of DukeHistory to add a Deadline task entry into it's records
     * using the generated description, date and time.
     *
     * @param tokens A String[] of tokens inputted by the user.
     * @param history An instance of DukeHistory.
     * @throws DukeException If the '/by' phrase is not detected in the String[] of tokens.
     */
    public void deadline(String[] tokens, DukeHistory history) throws DukeException {
        String description = "";
        String date = "";
        String time = "";
        int timeStart = -1; // -1 is a placeholder to indicate /by has not been found
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals("/by")) {
                timeStart = i;
                break;
            } else {
                description = description.concat(tokens[i]);
            }
            description = description.concat(" ");
        }
        // Check for Date and Time
        if (timeStart == -1 || tokens.length - timeStart < 3) {
            throw new DukeException("'/by' not detected");
        }
        // Handle Date
        date = date.concat(convertToDukeDate(tokens[timeStart + 1]));
        // Handle Time
        time = time.concat(convertToDukeTime(tokens[timeStart + 2]));
        history.addDeadline(description, date, time);
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
     */
    public void event(String[] tokens, DukeHistory history) throws DukeException {
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
        history.addEvent(description, date, time);
    }

    /**
     * A method that, when called, checks the validity of a given entry index before getting the inputted instance
     * of DukeHistory to delete that entry in its records.
     *
     * @param index An integer indicating the desired entry to delete.
     * @param history An instance of DukeHistory
     */
    public void delete(int index, DukeHistory history)  {
        if (index < 0 || index > history.getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
        history.deleteTask(index);
    }
}
