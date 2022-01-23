package duke.tasks;

public class Event extends Task {
    private String dateTime; // (at: date time)

    /**
     * Constructor for Event.
     * This is a Task with at a certain date/time.
     *
     * @param task     the task description
     * @param isDone   the mark status of this deadline
     * @param dateTime the date/time of this Task
     */
    public Event(String task, boolean isDone, String dateTime) {
        super(task, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Get the Command (in String form) to add this Event to the TaskList.
     * Useful for saving/reading from the save file.
     *
     * @return a String formatted specially for an Event.
     */
    public String getStringCmd() {
        // mark status | type | descriptor | datetime
        return super.getIsDone() + "&E&" + super.getTask() + "&" + this.dateTime;
    }

    /**
     * Retrieves the (at: ...) part of an Event.
     *
     * @return back part of an Event
     */
    public String getDateTime() {
        return "(at:" + this.dateTime + ")";
    }

    /**
     * Gets the String version of an Event.
     *
     * @return String-formatted Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + this.getDateTime();
    }
}
