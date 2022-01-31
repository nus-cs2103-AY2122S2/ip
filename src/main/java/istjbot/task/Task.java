package istjbot.task;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Encapsulates the concept of a task with String description,
 * boolean isDone to tell whether the task is marked, and an Optional of LocalDate object date
 * in case a date might not be present (such as Todo).
 */
public abstract class Task {
    /** String description for the task. */
    protected String description;
    /** A boolean value to tell whether the task is marked. */
    protected boolean isDone;
    /** An Optional of LocalDate object to indicate the set date for the task (if there is one). */
    protected Optional<LocalDate> date;

    /**
     * Constructor for this task.
     * isDone is set to false upon task creation.
     *
     * @param description String description for the task.
     * @param date LocalDate object that is contained within an Optional.
     */
    Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = Optional.ofNullable(date);
    }

    public abstract String toTxtString();

    /**
     * Marks a given task.
     * Sets isDone value to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Un-marks a given task.
     * Sets isDone value to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns an Optional of LocalDate object, the date associated with the task.
     *
     * @return An Optional of LocalDate object.
     */
    public Optional<LocalDate> getDate() {
        return this.date;
    }

    /**
     * Returns a String representation of whether the task is done (marked).
     *
     * @return String representation of marked or unmarked status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a String representation of a generic task.
     * Other child classes override it to add their own information.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
