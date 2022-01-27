public class Event extends Task {
    protected String dateInfo;
    protected DateParse date;
    protected TimeParse time;

    /**
     * Constructor for Event class
     * @param description Name of the Event
     * @param dateInfo Information for which this task is due
     */
    public Event(String description, String dateInfo) {
        super(description);
        String[] str = dateInfo.split(" ", 2);
        this.date = new DateParse(str[0]);
        this.time = new TimeParse(str[1]);
        this.dateInfo = this.date.toString() + " " + this.time.toString();
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
