package duke.info.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {

    private final String action;
    private boolean isComplete;
    private final String type;
    private String dateString;
    private LocalDate date;

    /**
     * Constructor for an abstract Task uses the specified action, type and
     * isComplete boolean. The dateString and date is instantiated as null
     * @param action - the action of the task
     * @param type - the type of the task
     * @param isComplete - whether the task is complete
     */
    Task(String action, String type, boolean isComplete) {
        this.action = action;
        this.type = type;
        this.isComplete = isComplete; // task added is not complete by default
        this.dateString = null;
        this.date = null;
    }

    /**
     * Constructor for an abstract Task with a specified date that uses the
     * specified action, type, isComplete boolean and dateString. If the dateString
     * can be parsed as a LocalDate, it will be saved as a LocalDate object. Otherwise,
     * it will be saved as a raw dateString.
     * @param action - the action of the task
     * @param type - the type of the task
     * @param isComplete - whether the task is complete
     * @param dateString - the string representation of the date of the task
     */
    Task(String action, String type, boolean isComplete, String dateString) {
        this.action = action;
        this.type = type;
        this.isComplete = isComplete;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d LLL yyyy");
        try {
            this.date = LocalDate.parse(dateString, dtf);
            this.dateString = null;
        } catch (DateTimeParseException e) {
            this.date = null;
            this.dateString = dateString;
        }
    }

    /**
     * Marks the task as complete by setting isComplete to true
     */
    void complete() {
        this.isComplete = true;
    }

    /**
     * Marks the task as incomplete by setting isComplete to false
     */
    void incomplete() {
        this.isComplete = false;
    }

    /**
     * Returns a save format representation of the task. To allow for easy parsing during
     * reloading, each field is delimited by a '|'.
     * @return - the save format representation of the task
     */
    public String saveFormat() {
        return String.format("%s|%s|%s", this.type, this.isComplete ? "1" : "0", this.action);
    }

    /**
     * Returns a boolean value that is true if and only if the task is complete
     * @return true if isComplete is true and false otherwise
     */
    public boolean getIsComplete() {
        return this.isComplete;
    }

    /**
     * Returns a string presentation of the action in the Task as specified by action.
     * @return - the string representation of the action required for the task
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Returns a string representation of the type of the task as specified in type
     * @return - the string representation of the type of the task
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns LocalDate representing the date of the event
     * @return - the LocalDate object representing the date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**j
     * Returns a string representation of the date of the task. If the date is saved as a
     * LocalDate object, it is returned as the format "d MMM yyyy". Otherwise the raw
     * dateString is returned
     * @return - the string representation of the date
     */
    public String getDateString() {
        if (this.date != null) {
            return this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } else {
            return this.dateString;
        }
    }

    /**
     * Returns true if and only if the task matches the specified keyword. The task
     * is considered as matching the keyword if its action contains the keyword as
     * determined by the String.contains method.
     * @param keyword - the keyword to be used in the matching
     * @return true if the keyword matches, false otherwise
     */
    public boolean matchesKeyword(String keyword) {
        return this.action.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns a string representation of this task. The string consists of a "[]" if the
     * task is incomplete or "[X]" if the task is complete, followed by the specified action
     * of the task.
     * @return - a string representation of the Task
     */
    @Override
    public String toString() {
        String product = "";
        if (isComplete) {
            product += "[X] ";
        } else {
            product += "[ ] ";
        }
        product += this.action;
        return product;
    }
}
