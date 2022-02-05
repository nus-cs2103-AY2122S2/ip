package duke;

/**
 * Represents the Event class that inherits from Task class.
 */
public class Event extends Task {
    private final String date;
    private final String time;

    /**
     * Constructor used to instantiate an Event class normally.
     *
     * @param description A String representing the description of the task.
     * @param date A String representing the date of the task.
     * @param time A String representing the time of the task.
     */
    public Event(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * An alternative constructor used to instantiate an Event class when loading data from a pre-existing\
     * duke.txt file at the start of a new Duke session.
     *
     * @param mark An integer indicating if the task was done or not.
     * @param description A String representing the description of the task.
     * @param date A String representing the date of the task.
     * @param time A String representing the time of the task.
     */
    public Event(int mark, String description, String date, String time) {
        super(description, mark);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the String representation of the Event class for user to read.
     *
     * @return A String representing the Event class for the user.
     */
    public String getEvent() {
        return "[E]" + this.getTask() + "(at: " + this.date + ", " + this.time + ")\n";
    }

    /**
     * Returns the String presentation of the Event class for writing into the duke.txt file.
     *
     * @return A String representing the Event class for the duke.txt file.
     */
    public String getFormattedText() {
        return "E>" + this.getMark() + ">" + this.getDescription() + ">" + this.date + ">" + this.time;
    }
}
