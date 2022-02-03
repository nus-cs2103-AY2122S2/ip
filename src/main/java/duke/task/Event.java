package src.main.java.duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import src.main.java.duke.DukeException;

/**
 * Event is a Task that should be attended at a certain date and time.
 */
public class Event extends Task {
    private static String type = "E";
    private String printedString;
    private LocalDate eventDate;
    private LocalTime eventTime;

    /**
     * Constructor for Event that takes in a description of the Event task and
     * whether it is marked as done.
     * 
     * @param description the description of the event task, containing the date and
     *                    time of the event
     * @param isDone      true if the event task has been marked as done
     * @throws DukeException exception thrown when the event datetime is invalid due
     *                       to missing input or improper input format
     */
    public Event(String description, boolean isDone) throws DukeException {
        super(type, description, isDone);
        try {
            String[] splitDescription = description.split("/at ");
            if (splitDescription.length > 1) {
                String[] dateTimeArray = splitDescription[1].split(" ");

                if (dateTimeArray.length > 1) {
                    this.eventDate = LocalDate.parse(dateTimeArray[0]);
                    this.eventTime = LocalTime.parse(dateTimeArray[1]);

                    this.printedString = dateTimeArray[0] + " (by: "
                            + this.eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                            + this.eventTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
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
     * Constructor for Event that takes in a description of the Event task.
     * 
     * @param description the description of the event task, containing the date and
     *                    time of the event
     * @throws DukeException exception thrown when the event datetime is invalid due
     *                       to missing input or improper input format
     */
    public Event(String description) throws DukeException {
        this(description, false);
    }

    /**
     * Returns the String representation of event task.
     * 
     * @return string representation of event task
     */
    @Override
    public String toString() {
        return this.isDone ? "[E][X] " + this.printedString
                : "[E][ ] " + this.printedString;
    }

}
