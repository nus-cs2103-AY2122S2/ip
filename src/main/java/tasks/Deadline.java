package tasks;

import tasks.Task;

/**
 * Method that allows a new deadline task.
 */
public class Deadline extends Task {

    /**
     * Class member that talks about the time of the deadline.
     */
    String time;

    /**
     * Constructor to create a new deadline task.
     * @param description Description of the deadline.
     * @param time Time of the deadline.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Getter to return the time of the deadline.
     * @return String time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Method that returns the string for the class.
     * @return String for the class.
     */
    @Override
     public String toString() {
         return String.format("[D] %s (by: %s)", super.toString(), time);
     }
    }
