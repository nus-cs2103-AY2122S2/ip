public class Event extends Task {
    private String at;

    /**
     * Constructor to create an Event Task.
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

   
    public Event(String name, String at, boolean done) {
        super(name, done);
        this.at = at;
    }

    /**
     * Getter for the date of the Event.
     * @return The date of the Event.
     */
    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}