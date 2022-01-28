package duke;

import exceptions.DukeDeadlineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Extends Task class
 * Represents events with deadline
 */
public class Deadline extends Task {
    protected LocalDate by_date;

    /**
     * Constructor
     * Takes in name of task and deadline
     * @param name String name of task
     * @param by_date LocalDate task deadline
     */
    public Deadline(String name, LocalDate by_date) {
        super(name);
        this.by_date = by_date;
    }

    /**
     * Static method to return a Deadline object
     * @param input String to be parsed
     * @return Deadline Task object
     * @throws DukeDeadlineException
     */
    public static Deadline setDeadline(String input) throws DukeDeadlineException { //method to call constructor solving
                                                    // "'this' should be called in first line" error
        String taskname;
        String taskby;
        LocalDate taskby_date;

        try {
            String[] split = input.split(" /by ");
            taskname = split[0];
            taskby = split[1];

            taskby_date = LocalDate.parse(taskby);

            Deadline d_line = new Deadline(taskname, taskby_date);
            return d_line;
        } catch (Exception e) {
            DukeDeadlineException error = new DukeDeadlineException(
                                                    "OOPS!!! Please enter in format: deadline <task> /by <yyyy-mm-dd> \n " +
                                                    "e.g. deadline complete project /by 2022-12-24");
            System.out.println(error.getMessage());
        }
        return null;
    }

    /**
     * Getter for Deadline task
     * @return String parsed LocalDate
     */
    public String getDate() {
        return by_date.toString();
    }

    @Override
    public String toString() {
        String formatted = by_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by:" + formatted + ")";
    }
}
