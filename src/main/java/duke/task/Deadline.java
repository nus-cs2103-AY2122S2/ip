package src.main.java.duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import src.main.java.duke.DukeException;

/**
 * Deadline is a Task that should be done by a certain date and time.
 */
public class Deadline extends Task {
    private static String type = "D";
    private String printed;
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for Deadline that takes in a description of the Deadline task and
     * whether it is marked as done.
     * 
     * @param description the description of the deadline task, containing the date
     *                    and time of deadline
     * @param isDone      true if the deadline task has been marked as done
     * @throws DukeException exception thrown when the deadline is invalid due to
     *                       missing deadline or improper input format
     */
    public Deadline(String description, boolean isDone) throws DukeException {
        super(type, description, isDone);
        try {
            String[] temp = description.split("/by ");
            if (temp.length > 1) {
                String[] temp2 = temp[1].split(" ");

                if (temp2.length > 1) {
                    this.date = LocalDate.parse(temp2[0]);
                    this.time = LocalTime.parse(temp2[1]);

                    this.printed = temp[0] + " (by: "
                            + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                            + this.time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
                } else {
                    throw new DukeException(
                            "Please include the time in the deadline in the following manner: yyyy-mm-dd hh:mm");
                }
            } else {
                throw new DukeException("Please input a deadline in the following manner: yyyy-mm-dd hh:mm");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in the proper manner: yyyy-mm-dd hh:mm");
        }
    }

    /**
     * Constructor for Deadline that takes in a description of the Deadline task
     * 
     * @param description the description of the deadline task, containing the date
     *                    and time of deadline
     * @throws DukeException exception thrown when the deadline is invalid due to
     *                       missing deadline or improper input format
     */

    public Deadline(String description) throws DukeException {
        this(description, false);
    }

    /**
     * toString method is overriden to ensure that the String representation of the
     * Deadline task is readable
     */
    @Override
    public String toString() {
        return this.isDone ? "[D][X] " + this.printed
                : "[D][ ] " + this.printed;
    }
}
