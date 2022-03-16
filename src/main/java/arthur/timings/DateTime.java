package arthur.timings;

import static arthur.commons.Messages.REMINDER_NO_TASKS_TEMPLATE;
import static arthur.commons.Messages.REMINDER_TEMPLATE;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import arthur.TaskList;
import arthur.exceptions.InvalidStoredDataFormat;
import arthur.task.Deadline;
import arthur.task.Event;
import arthur.task.Task;

/**
 * Handles string conversion to Date and Time objects.
 */
public class DateTime {
    private static final String USER_INPUT_DATE_SPLITTER = "-";
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hh:mma");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateFormat STORED_TIME_FORMAT = new SimpleDateFormat("hh:mma");
    private static final DateFormat STORED_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");
    private static final DateFormat STORED_DATE_TIME_FORMAT = new SimpleDateFormat(
            "dd MMM yyyy hh:mma");
    private static final DateFormat USER_TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final DateFormat USER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat USER_DATE_TIME_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    private final String str;

    /**
     * Constructs the datetime object.
     */
    public DateTime() {
        this.str = "";
    }
    /**
     * Converts input string to date and time objects.
     *
     * @param input Formatted user input as string.
     * @throws DateTimeParseException Thrown when the date and/or time is not formatted properly.
     */
    public DateTime(String input) throws DateTimeParseException {
        // Checks if date is present
        LocalTime time;
        if (input.contains(USER_INPUT_DATE_SPLITTER)) {
            String[] dateTimeArr = input.split(" ");
            boolean hasDateAndTime = dateTimeArr.length == 2;
            LocalDate date;
            if (hasDateAndTime) {
                date = LocalDate.parse(dateTimeArr[0]);
                time = LocalTime.parse(dateTimeArr[1]);
                this.str = date.format(DATE_FORMAT) + " " + time.format(TIME_FORMAT);
            } else {
                date = LocalDate.parse(input);
                this.str = date.format(DATE_FORMAT);
            }
        } else {
            time = LocalTime.parse(input);
            this.str = time.format(TIME_FORMAT);
        }
    }

    public String getString() {
        return this.str;
    }

    /**
     * Checks the date with current date to see if they are the same.
     *
     * @param tasklist The tasklist with list of tasks info.
     * @return A string of all the tasks due on the current date.
     */
    public static String checkDate(TaskList tasklist) {
        StringBuilder result = new StringBuilder(REMINDER_TEMPLATE);
        String currDate = LocalDate.now().format(DATE_FORMAT);
        boolean hasTaskToRemind = false;
        for (int i = 0; i < tasklist.tasksSize(); i++) {
            Task currTask = tasklist.getTask(i);
            if (currTask instanceof Deadline) {
                DateTime taskTiming = ((Deadline) currTask).getTiming();
                boolean isSame = taskTiming.str.equals(currDate);
                if (isSame) {
                    result.append(currTask).append("\n");
                    hasTaskToRemind = true;
                }
            }
            if (currTask instanceof Event) {
                DateTime taskTiming = ((Event) currTask).getTiming();
                String taskDate = taskTiming.str.substring(0, 11);
                boolean isSame = taskDate.equals(currDate);
                if (isSame) {
                    result.append(currTask).append("\n");
                    hasTaskToRemind = true;
                }
            }
        }
        return hasTaskToRemind
                ? result.toString()
                : REMINDER_NO_TASKS_TEMPLATE;
    }

    /**
     * Converts string formatted date in storage file to user input format.
     *
     * @param input Stored string version of date.
     * @return User input version of date.
     */
    public String stringToDateFormat(String input) throws InvalidStoredDataFormat {
        String[] dateTimeArr = input.split(" ");
        boolean hasOnlyTime = dateTimeArr.length == 1;
        boolean hasOnlyDate = dateTimeArr.length == 3;
        boolean hasBothDateTime = dateTimeArr.length == 4;
        StringBuilder result = new StringBuilder();
        try {
            Date date;
            if (hasOnlyTime) {
                date = STORED_TIME_FORMAT.parse(input);
                result.append(USER_TIME_FORMAT.format(date));
            } else if (hasOnlyDate) {
                date = STORED_DATE_FORMAT.parse(input);
                result.append(USER_DATE_FORMAT.format(date));
            } else if (hasBothDateTime) {
                date = STORED_DATE_TIME_FORMAT.parse(input);
                result.append(USER_DATE_TIME_FORMAT.format(date));
            } else {
                throw new InvalidStoredDataFormat();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
