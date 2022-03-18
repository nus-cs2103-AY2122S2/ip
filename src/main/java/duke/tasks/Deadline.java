package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Deadline is a Task that should be done by a certain date and time.
 */
public class Deadline extends Task {
    private static String type = "D";
    private String printedString;
    private String descriptionWithoutDateTime;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Constructor for Deadline that takes in a description of the Deadline task and
     * whether it is marked as done.
     * @param description the description of the deadline task, containing the date
     *                    and time of deadline
     * @param isDone      true if the deadline task has been marked as done
     * @throws DukeException exception thrown when the deadline is invalid due to
     *                       missing deadline or improper input format
     */
    public Deadline(String description, boolean isDone) throws DukeException {
        this.isDone = isDone;
        this.description = description;
        try {
            String[] splitDescription = description.split("/by ");
            descriptionWithoutDateTime = splitDescription[0];
            boolean containsDateTimeSpecification = splitDescription.length > 1;

            if (containsDateTimeSpecification) {
                String dateAndTime = splitDescription[1];
                String[] splitDateTime = dateAndTime.split(" ");
                boolean containsTimeSpecification = splitDateTime.length > 1;

                if (containsTimeSpecification) {
                    this.deadlineDate = LocalDate.parse(splitDateTime[0]);
                    this.deadlineTime = LocalTime.parse(splitDateTime[1]);

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
     * Constructor for Deadline that takes in a description of the Deadline task
     * @param description the description of the deadline task, containing the date
     *                    and time of deadline
     * @throws DukeException exception thrown when the deadline is invalid due to
     *                       missing deadline or improper input format
     */

    public Deadline(String description) throws DukeException {
        this(description, false);
    }

    private void updatePrintedString() {
        assert descriptionWithoutDateTime != null;
        assert deadlineDate != null;
        assert deadlineTime != null;

        this.printedString = descriptionWithoutDateTime + " (by: "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    @Override
    public void updateDate(String dateString) throws DukeException {
        try {
            this.deadlineDate = LocalDate.parse(dateString);
            updatePrintedString();
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to update deadline date due to improper input");
        }
    }

    @Override
    public void updateTime(String timeString) throws DukeException {
        try {
            this.deadlineTime = LocalTime.parse(timeString);
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
     * Returns the String representation of deadline task.
     * @return string representation of deadline task
     */
    @Override
    public String toString() {
        assert this.printedString != null;

        return this.isDone ? "!!!DONE!!! Deadline - " + this.printedString
                : "Deadline - " + this.printedString;
    }

    @Override
    public String getType() {
        return type;
    }
}
