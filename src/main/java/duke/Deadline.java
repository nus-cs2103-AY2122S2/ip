package duke;

import exceptions.DukeDeadlineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Extends Task class
 * Represents events with deadline
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    /**
     * Constructor
     * Takes in name of task and deadline
     * @param name String name of task
     * @param byDate LocalDate task deadline
     */

    public Deadline(String name, LocalDate byDate) {
        super(name);
        this.byDate = byDate;
    }

    /**
     * Static method to return a Deadline object
     * @param input String to be parsed
     * @return Deadline Task object
     * @throws DukeDeadlineException
     */
    public static Deadline setDeadline(String input) throws DukeDeadlineException { //method to call constructor solving
                                                    // "'this' should be called in first line" error
        String taskName;
        String taskBy;
        LocalDate taskByDate;

        try {
            String[] split = input.split(" /by ");
            taskName = split[0];
            taskBy = split[1];

            taskByDate = LocalDate.parse(taskBy);

            Deadline d_line = new Deadline(taskName, taskByDate);
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
        return byDate.toString();
    }

    /**
     * Getter for Deadline task name
     * @return String represents name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String formatted = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by:" + formatted + ")";
    }
}
