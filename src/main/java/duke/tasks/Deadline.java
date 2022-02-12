package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a class which represents the deadline task.
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;

    /**
     * Creates a Deadline task object, constructor.
     *
     * @param description A description of the task.
     * @param deadlineDate The date which the task must be completed.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Formats the description of the task into a standardised format to
     * be saved to a file.
     *
     * @return A String which is to be saved in a file.
     */
    @Override
    public String formatString() {
        String output = "D";
        String markState = this.isDone ? "mark" : "unmark";
        return output + " | " + markState + " | "
                + this.description + " | " + this.deadlineDate;
    }

    /**
     * Checks if the current Deadline task is equal
     * to the Deadline task passed into the function.
     *
     * @param task The Deadline task to be checked.
     * @return True if equal, false otherwise.
     */
    public boolean equalsTo(Deadline task) {
        String descriptionToCheck = task.getDescription();
        String currentDescription = this.getDescription();
        boolean isDescriptionSame = descriptionToCheck.equals(currentDescription);
        LocalDate dateToCheck = task.getDeadlineDate();
        LocalDate currentDate = this.deadlineDate;
        boolean isDateSame = dateToCheck.isEqual(currentDate);

        return isDescriptionSame && isDateSame;
    }

    /**
     * Gets the date of the deadline of the task.
     *
     * @return A LocalDate object representing the deadline.
     */
    LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    /**
     * Returns the String representation of the deadline task.
     *
     * @return A String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
