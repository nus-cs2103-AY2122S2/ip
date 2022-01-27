public class Deadline extends Task {
    protected String dateInfo;

    /**
     * Constructor for Deadline class
     * @param description Name of the Deadline
     * @param dateInfo Information for which this task is due
     */
    public Deadline(String description, String dateInfo) {
        super(description);
        this.dateInfo = dateInfo;
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
