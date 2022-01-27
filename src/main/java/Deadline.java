public class Deadline extends Task {
    protected String dateInfo;
    protected DateParse date;
    protected TimeParse time;

    /**
     * Constructor for Deadline class
     * @param description Name of the Deadline
     * @param dateInfo Information for which this task is due
     */
    public Deadline(String description, String dateInfo) {
        super(description);
        String[] str = dateInfo.split(" ", 2);
        this.date = new DateParse(str[0]);
        this.time = new TimeParse(str[1]);
        this.dateInfo = this.date.toString() + " " + this.time.toString();
    }

    public String whatType() {
        return "D";
    }

    /**
     * toString method specific for Deadline class,
     * inherits toString() fromTask class while adding additional information
     * Like the type of task, [D], and date information
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateInfo + ")";
    }
}
