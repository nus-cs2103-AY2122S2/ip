package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Event is a Task that should be attended at a certain date and time.
 */
public class Event extends Task {
    private static String type = "E";
    private String printedString;
    private String descriptionWithoutDateTime;
    private LocalDate eventDate;
    private LocalTime eventTime;

    /**
     * Constructor for Event that takes in a description of the Event task and
     * whether it is marked as done.
     * @param description the description of the event task, containing the date and
     *                    time of the event
     * @param isDone      true if the event task has been marked as done
     * @throws DukeException exception thrown when the event datetime is invalid due
     *                       to missing input or improper input format
     */
    public Event(String description, boolean isDone) throws DukeException {
        this.description = description;
        this.isDone = isDone;
        try {
            String[] splitDescription = description.split("/at ");
            descriptionWithoutDateTime = splitDescription[0];
            boolean containsDateTimeSpecification = splitDescription.length > 1;

            if (containsDateTimeSpecification) {
                String dateAndTime = splitDescription[1];
                String[] splitDateTime = dateAndTime.split(" ");
                boolean containsTimeSpecification = splitDateTime.length > 1;

                if (containsTimeSpecification) {
                    this.eventDate = LocalDate.parse(splitDateTime[0]);
                    this.eventTime = LocalTime.parse(splitDateTime[1]);

                    updatePrintedString();
                } else {
                    throw new DukeException(DukeException.INVALID_FORMAT);
                }
            } else {
                throw new DukeException(DukeException.INVALID_FORMAT);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.INVALID_FORMAT);
        }
    }

    /**
     * Constructor for Event that takes in a description of the Event task.
     * @param description the description of the event task, containing the date and
     *                    time of the event
     * @throws DukeException exception thrown when the event datetime is invalid due
     *                       to missing input or improper input format
     */
    public Event(String description) throws DukeException {
        this(description, false);
    }

    private void updatePrintedString() {
        assert descriptionWithoutDateTime != null;
        assert eventDate != null;
        assert eventTime != null;

        this.printedString = descriptionWithoutDateTime + " (at: "
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + this.eventTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    @Override
    public void updateDate(String dateString) throws DukeException {
        try {
            this.eventDate = LocalDate.parse(dateString);
            updatePrintedString();
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to update deadline date due to improper input");
        }
    }

    @Override
    public void updateTime(String timeString) throws DukeException {
        try {
            this.eventTime = LocalTime.parse(timeString);
            updatePrintedString();
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to update deadline time due to improper input");
        }
    }

    @Override
    public void updateDescription(String newDescription) {
        this.descriptionWithoutDateTime = newDescription;
        updatePrintedString();
    }

    /**
     * Returns the String representation of event task.
     * @return string representation of event task
     */
    @Override
    public String toString() {
        assert this.printedString != null;
        return this.isDone ? "!!!DONE!!! Event - " + this.printedString
                : "Event - " + this.printedString;
    }

    @Override
    public String getType() {
        return type;
    }

}
