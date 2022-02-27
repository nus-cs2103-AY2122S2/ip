package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import duke.exception.InvalidArgumentException;

/**
 * A Deadline is a specialization of a Task.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs Deadline class.
     * Event constructor accepts the description of the task and when the task's deadline.
     *
     * @param name Description of the deadline task.
     * @param by   Time indicator of the deadline task.
     */
    Deadline(String name, String by) {
        super(name);
        if (isValid(by)) {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else {
            this.by = LocalDate.now();
        }
    }

    /**
     * Validates whether the input is a date.
     *
     * @param date Input value to validate.
     * @return True if the input value is a date, false otherwise.
     */
    private Boolean isValid(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether this deadline is within the time range given.
     * This is to categorize whether deadline should be reminded or not.
     *
     * @param inputNumber User input number.
     * @param timeIdentifier time id which consists of day, week, or month.
     * @return True if deadline is within the time range given.
     * @throws InvalidArgumentException if timeIdentifier is invalid.
     */
    public Boolean happensBefore(int inputNumber, String timeIdentifier) throws InvalidArgumentException {
        if (this.getStatus()) {
            return false;
        }
        int timeMultiplier;
        switch(timeIdentifier) {
        case "day":
        case "days":
            timeMultiplier = 1;
            break;
        case "week":
        case "weeks":
            timeMultiplier = 7;
            break;
        case "month":
        case "months":
            timeMultiplier = 30;
            break;
        default:
            throw new InvalidArgumentException();
        }
        int numOfDays = inputNumber * timeMultiplier;
        return ChronoUnit.DAYS.between(LocalDate.now(), by) <= numOfDays;
    }

    /**
     * Constructs a Deadline object as a factory constructor.
     *
     * @param description Description of the Deadline task in array format.
     * @return the Deadline object.
     * @throws InvalidArgumentException If there is no description or time indicator.
     */
    public static Deadline of(String[] description) throws InvalidArgumentException {
        return Deadline.of(Arrays.asList(description));
    }

    /**
     * Constructs a Deadline object as a factory constructor.
     *
     * @param description Description of the deadline task in List format.
     * @return the Deadline object.
     * @throws InvalidArgumentException If there is no description or time indicator.
     */
    public static Deadline of(List<String> description) throws InvalidArgumentException {
        if (description.size() == 1) {
            throw new InvalidArgumentException();
        }
        int index = description.indexOf("/by");
        String name = String.join(" ", description.subList(1, index));
        String by = String.join(" ", description.subList(index + 1, description.size()));
        return new Deadline(name, by);
    }

    /**
     * Returns text representing the deadline task.
     * This method is used for storing deadline task.
     *
     * @return Task in text format.
     */
    @Override
    public String toStorageString() {
        String status = getStatus() ? "X" : ".";
        return String.format(status + " deadline " + getName() + " /by " + by);
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    /**
     * Returns text representing the deadline task for User.
     *
     * @return Task in text format.
     */
    @Override
    public String toString() {
        return String.format("[D]"
                + super.toString()
                + "(by: "
                + by.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")");
    }
}
