package tasks;

/**
 * Class to represent an event task
 */
public class Event extends Task {

    /**
     * String time for the event
     */
    String time;

    /**
     * Constructor for the event task.
     * @param description Description for the event.
     * @param time Time of the event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Method to get the time of the task.
     * @return String time of the event.
     */
    public String getTime() {
        return time;
    }

    /**
     * Method to get the String for the event.
     * @return String for the class
     */
    @Override
     public String toString() {
         return String.format("[E] %s (at: %s)", super.toString(), time);
     }
    }
