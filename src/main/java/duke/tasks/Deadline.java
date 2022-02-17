package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import duke.exceptions.DukeException;
import duke.interfaces.Timable;

/**
 * Represents a task with a deadline.
 * Stores the date and time(if applicable) of the deadline tasks
 */
public class Deadline extends Task implements Timable {

    protected LocalDate date;
    protected LocalTime time;
    protected boolean hasTime;

    /**
     * Creates an instance of a Deadline object.
     *
     * @param description the details of the deadline task.
     * @param date the deadline(date) of the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        hasTime = false;
    }

    /**
     * Creates an instance of a Deadline object.
     *
     * @param description the details of the deadline task.
     * @param date the deadline(date) of the task.
     * @param time the deadline(time) of the task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        hasTime = true;
    }

    /**
     * Gets the dateTime representation of a deadline, with the date being
     * the date of the deadline and time(if applicable) being the time of the deadline task.
     * @return the dateTime representation of this deadline task.
     */
    @Override
    public LocalDateTime getDateTime() {
        if (hasTime) {
            return LocalDateTime.of(date, time);
        } else {
            return LocalDateTime.of(date, LocalTime.MIN);
        }
    }

    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                    + time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    /**
     * The factory function of the Deadline task.
     * @param tokens the specification of the Deadline task which contains
     *               the description and date/time of the task.
     * @throws DukeException if the provided tokens does not satisfy the required format to create
     *                       a deadline task.
     */
    protected static Deadline createTask(String[] tokens) throws DukeException {

        String description = createDescription(tokens);
        LocalDate date = null;
        LocalTime time = null;
        String errorMsg = "";
        boolean error = false;

        if (description.equals("")) {
            error = true;
            errorMsg += "The description of a deadline task cannot be empty!\n";
        }

        try {
            date = createDate(tokens);
            time = createTime(tokens);
        } catch (IllegalArgumentException e) {
            error = true;
            errorMsg += e.getMessage() + "\n";
        }
        if (error) {
            errorMsg += "Please enter your command in the form of - "
                    + "deadline <task information> /by <date> <time>(optional)\n";
            errorMsg += "e.g. deadline buy book /by 2015-05-05 06:00";
            throw new DukeException(errorMsg.trim());
        }

        if (time == null) {
            return new Deadline(description, date);
        } else {
            return new Deadline(description, date, time);
        }
    }

    private static String createDescription(String[] tokens) {
        String item = "";
        for (int idx = 0; idx < tokens.length; idx++) {
            if (tokens[idx].equals("deadline")) {
                continue;
            } else if (tokens[idx].equals("/by")) {
                idx++;
                break;
            }
            item += tokens[idx] + " ";
        }
        return item.trim();
    }

    private static LocalDate createDate(String[] tokens) throws IllegalArgumentException {
        int idx = 0;
        for (; idx < tokens.length; idx++) {
            if (tokens[idx].equals("/by")) {
                break;
            }
        }
        try {
            String dateString = tokens[idx + 1];
            return LocalDate.parse(dateString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date entered!");
        }
    }

    private static LocalTime createTime(String[] tokens) throws IllegalArgumentException {
        int idx = 0;
        for (; idx < tokens.length; idx++) {
            if (tokens[idx].equals("/by")) {
                break;
            }
        }
        int timeIndex = idx + 2;
        if (timeIndex == tokens.length) {
            return null;
        }

        try {
            String timeString = tokens[timeIndex];
            return LocalTime.parse(timeString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time entered!");
        }
    }
}
