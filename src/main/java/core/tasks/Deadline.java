package core.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import core.exceptions.NoDeadlineMentionedException;
import core.exceptions.NoDescriptionGivenException;
import utilities.OutputFormatter;

/**
 * The Deadline class.
 *
 * @author s7manth
 * @version 0.1
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;
    protected String by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the deadline.
     * @param by The time by which the deadline is supposed to be achieved.
     */
    private Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.split(" ");
        this.byDate = LocalDate.parse(dateAndTime[0]);
        if (dateAndTime.length > 1) {
            this.byTime = LocalTime.parse(dateAndTime[1]);
        }
        this.by = by;
    }

    /**
     * Method to obtain the label for the Deadline class.
     *
     * @return The label of Deadline.
     */
    @Override
    public String getLabel() {
        return "D";
    }

    /**
     * Obtains peripheral information about the Deadline object.
     *
     * @return The peripheral info about the Deadline.
     */
    @Override
    public String getPeripheralInfo() {
        return this.by;
    }

    /**
     * Factory method to obtain an instance of the Deadline class.
     *
     * @param description The description of the Deadline.
     * @param by The time by which the deadline must be achieved.
     * @return An instance of the Deadline class with the specified description and by time.
     * @throws NoDeadlineMentionedException Throws an exception if no deadline is mentioned.
     * @throws NoDescriptionGivenException Throws an exception if the description is empty or blank.
     * @throws DateTimeParseException Throws an exception if the deadline entered is in an incorrect format.
     */
    public static Deadline getInstance(String description, String by) throws
            NoDeadlineMentionedException, NoDescriptionGivenException, DateTimeParseException {
        if (description.isBlank() || description.isBlank()) {
            throw new NoDescriptionGivenException();
        }

        if (by.isBlank() || by.isEmpty()) {
            throw new NoDeadlineMentionedException();
        }
        return new Deadline(description, by);
    }

    /**
     * Obtains a string representation of the Deadline object.
     *
     * @return String representing the Deadline object.
     */
    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[D]", super.toString(), " (by: ",
                byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (byTime != null) {
            outputFormatter.appendAll(" ", byTime.format(DateTimeFormatter.ofPattern("Ka")), ")");
            return outputFormatter.getFormattedOutput();
        }
        outputFormatter.append(")");
        return outputFormatter.getFormattedOutput();
    }
}
