public class Event extends Task {
    protected String dateInfo;

    /**
     * Constructor for Task class, set isDone to false by default
     * @param description Name of the to-do task
     */
    public Event(String description, String dateInfo) {
        super(description);
        this.dateInfo = dateInfo;
    }
//    public Event(String description, String dateInfo) {
//        super(description, dateInfo, "dummy");
//    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + this.dateInfo + ")";
    }
}
