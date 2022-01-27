public class Event extends Task {
    protected String dateInfo;

    /**
     * Constructor for Event class
     * @param description Name of the Event
     * @param dateInfo Information for which this task is due
     */
    public Event(String description, String dateInfo) {
        super(description);
        this.dateInfo = dateInfo;
    }

    public String whatType() {
        return "E";
    }

    /**
     * toString method specific for Event class,
     * inherits toString() fromTask class while adding additional information
     * Like the type of task, [E], and date information
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateInfo + ")";
    }
}
