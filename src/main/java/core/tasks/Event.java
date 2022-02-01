package core.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import core.exceptions.NoDescriptionGivenException;
import core.exceptions.NoEventLocaleMentionedException;
import utilities.OutputFormatter;

/**
 * The Event class.
 *
 * @author s7manth
 * @version 0.1
 */
public class Event extends Task {
    protected LocalDate atDate;
    protected LocalTime atTime;
    protected String at;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event.
     * @param at The locale of the event.
     */
    private Event(String description, String at) {
        super(description);
        String[] dateAndTime = at.split(" ");
        this.atDate = LocalDate.parse(dateAndTime[0]);
        if (dateAndTime.length > 1) {
            this.atTime = LocalTime.parse(dateAndTime[1]);
        }
        this.at = at;
    }

    /**
     * Factory method to obtain an instance of the Event class.
     *
     * @param description The description of the event.
     * @param at The locale of the event.
     * @return An instance of the Event with the specified description and locale.
     * @throws DateTimeParseException Throws an exception if the specified locale for the event is in incorrect format.
     * @throws NoEventLocaleMentionedException Throws an exception if the locale is absent for the event.
     * @throws NoDescriptionGivenException Throws an exception if the description is blank or empty.
     */
    public static Event getInstance(String description, String at) throws
            DateTimeParseException, NoEventLocaleMentionedException, NoDescriptionGivenException {
        if (description.isBlank() || description.isBlank()) {
            throw new NoDescriptionGivenException();
        }

        if (at.isEmpty() || at.isBlank()) {
            throw new NoEventLocaleMentionedException();
        }
        return new Event(description, at);
    }

    /**
     * Method to obtain peripheral info about the event.
     *
     * @return The locale of the event.
     */
    @Override
    public String getPeripheralInfo() {
        return this.at;
    }

    /**
     * Method to obtain the label for the Event class.
     *
     * @return The label of Event class.
     */
    @Override
    public String getLabel() {
        return "E";
    }

    /**
     * Obtain a string representation of the Event object.
     *
     * @return The string representing the event object.
     */
    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[E]", super.toString(), " (at: ",
                atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (atTime != null) {
            outputFormatter.appendAll(" ", atTime.format(DateTimeFormatter.ofPattern("Ka")), ")");
            return outputFormatter.getFormattedOutput();
        }
        outputFormatter.append(")");
        return outputFormatter.getFormattedOutput();
    }
}
