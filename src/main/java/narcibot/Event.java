package narcibot;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String time;

    /**
     * Constructor for event class
     * @param name name of the event
     * @param time time of the event
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
        try {
            LocalDate date = LocalDate.parse(time);
            System.out.println("[E][ ] " + name + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        } catch (DateTimeParseException e) {
            System.out.println("[E][ ] " + name + " (at: " + time + ")");
        }
    }

    /**
     * Constructor for event class with indication of the status
     * @param name name of event
     * @param time time of event
     * @param done status of event
     */
    public Event(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    /**
     * Gets status of event
     * @return String with format of [E][status] name (at: time)
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + time +")";
    }

    /**
     * Mark event as done.
     */
    @Override
    public void markDone() {
        System.out.print("[E]");
        super.markDone();
        System.out.println(" (at: " + time +")");
    }

    /**
     * Mark event as not done.
     */
    @Override
    public void markNotDone() {
        System.out.print("[E]");
        super.markNotDone();
        System.out.println(" (at: " + time +")");
    }

    /**
     * Save the event in the format
     * @return String with the required format
     */
    @Override
    public String save() {
        return "E|" + super.save() + "|" + time;
    }
}
