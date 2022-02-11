package arthur.timings;

import arthur.TaskList;
import arthur.task.Deadline;
import arthur.task.Event;
import arthur.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles string conversion to Date and Time objects.
 */
public class DateTime {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hh:mma");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final String REMINDER_TEMPLATE = "Tasks due today: \n";
    private final String str;

    /**
     * Converts input string to date and time objects.
     * @param input Formatted user input as string
     * @throws DateTimeParseException Throws if the date and/or time is not formatted properly
     */
    public DateTime(String input) throws DateTimeParseException {
        // Checks if date is present
        LocalTime time;
        if (input.contains("-")) {
            String[] tempArr = input.split(" ");
            LocalDate date;
            if (tempArr.length == 2) {
                date = LocalDate.parse(tempArr[0]);
                time = LocalTime.parse(tempArr[1]);
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

    public static String checkDate(TaskList tasklist) {
        StringBuilder result = new StringBuilder(REMINDER_TEMPLATE);
        String currDate = LocalDate.now().format(DATE_FORMAT);
        for (int i = 0; i < tasklist.tasksSize(); i++) {
            Task currTask = tasklist.getTask(i);
            if (currTask instanceof Deadline) {
                DateTime taskTiming = ((Deadline) currTask).getTiming();
                boolean isSame = taskTiming.str.equals(currDate);
                if (isSame) {
                    result.append(currTask).append("\n");
                }
            } else if (currTask instanceof Event) {
                DateTime taskTiming = ((Event) currTask).getTiming();
                String taskDate = taskTiming.str.substring(0, 11);
                boolean isSame = taskDate.equals(currDate);
                if (isSame) {
                    result.append(currTask).append("\n");
                }
            }
        }
        return result.toString();
    }
}
