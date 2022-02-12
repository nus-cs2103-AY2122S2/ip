package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a class which represents the event task.
 */
public class Event extends Task {
    protected LocalDate eventDate;

    /**
     * Creates an Event task object, constructor.
     * @param description A description of the task.
     * @param eventDate The date which the event happens.
     */
    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Formats the description of the task into a standardised format to
     * be saved to a file.
     *
     * @return A String which is to be saved in a file.
     */
    @Override
    public String formatString() {
        String output = "E";
        String markState = this.isDone ? "mark" : "unmark";
        return output + " | " + markState + " | "
                + this.description + " | " + this.eventDate;
    }

    /**
     * Checks if the current Event task is equal
     * to the Event task passed into the function.
     *
     * @param task The Event task to be checked.
     * @return True if equal, false otherwise.
     */
    public boolean equalsTo(Event task) {
        String descriptionToCheck = task.getDescription();
        String currentDescription = this.getDescription();
        boolean isDescriptionSame = descriptionToCheck.equals(currentDescription);
        LocalDate dateToCheck = task.getEventDate();
        LocalDate currentDate = this.eventDate;
        boolean isDateSame = dateToCheck.isEqual(currentDate);

        return isDescriptionSame && isDateSame;
    }

    /**
     * Gets the date of the Event task.
     *
     * @return A LocalDate object representing the date of Event.
     */
    LocalDate getEventDate() {
        return this.eventDate;
    }

    /**
     * Returns the String representation of the Event task.
     *
     * @return A String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
