package karen.task;

import java.time.LocalDate;

/**
 * Abstract class to store description and status of Task
 */
public abstract class Task {
    public String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * To set done status of Task to true
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * To set done status of Task to false
     */
    public void markUndone() {
        this.done = false;
    }

    /**
     * To parse done attribute of Task object into a String representation.
     * @return String representation of done attribute of Task
     */
    public String getDoneIcon () {
        return (this.done) ? "X" : " ";
    }

    /**
     * Abstract method to parse Task objects into a String object representation
     * for writing to file
     * @return String representation of attributes or data related to Task for storage
     */
    public abstract String toSaveData();

    /**
     * Accepts string in yyyy-mm-dd format
     * @param dateString String object input
     * @return LocalDate object representation of dateString parameter
     */
    public LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    /**
     * To get String representation of Task object.
     * For example: [X] example-task
     * @return String representation of Task object
     */
    @Override
    public String toString() {
        String status = this.getDoneIcon();
        return String.format("[%s] %s", status, this.description);
    }
}
