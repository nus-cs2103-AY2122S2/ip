public class EventTask extends Task{
    private String date;

    /**
     * Constructor for an event task.
     *
     * @param name name of the event
     * @param date date of the event
     */
    public EventTask(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
