public class Deadline extends Task {
    protected String dateInfo;

    /**
     * Constructor for Task class, set isDone to false by default
     * @param description Name of the to-do task
     */
    public Deadline(String description, String dateInfo) {
        super(description);
        this.dateInfo = dateInfo;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.dateInfo + ")";
    }
}
