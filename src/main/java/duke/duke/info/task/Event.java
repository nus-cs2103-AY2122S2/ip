package duke.info.task;

public class Event extends Task {

    /**
     * Constructs an Event object with the specified event, date, isComplete
     * @param event - the event to be added
     * @param date - the date of the event to be added
     * @param isComplete - whether the task is complete
     */
    public Event(String event, String date, boolean isComplete) {
        super(event, "event", isComplete, date);
    }

    /**
     * Returns the save format of the Event. The string contains the save format representation from
     * the task superclass, followed by the string representation of the date as obtained from the
     * getDateString of the superclass method delimited by a "|".
     * @return - the save format of the Event as a string
     */
    @Override
    public String saveFormat() {
        return super.saveFormat() + "|" + this.getDateString();
    }

    /**
     * Returns a string presentation of the Event. The string consists of the type of the task
     * contained by "[]", the task description, followed by the date of the Event as obtained
     * by the super.getDateString method.
     * @return - a string representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.getDateString());
    }
}
