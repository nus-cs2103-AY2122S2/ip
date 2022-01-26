package narcibot;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String time;
    public Event(String name, String time) {
        super(name);
        this.time = time;
        try {
            LocalDate date = LocalDate.parse(time);
            System.out.println("[E][ ] " + name + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        } catch (DateTimeParseException e) {
            System.out.println("[E][ ] " + name + " (by: " + time + ")");
        }
    }

    public Event(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + time +")";
    }

    @Override
    public void markDone() {
        System.out.print("[E]");
        super.markDone();
        System.out.println(" (at: " + time +")");
    }

    @Override
    public void markNotDone() {
        System.out.print("[E]");
        super.markNotDone();
        System.out.println(" (at: " + time +")");
    }

    @Override
    public String save() {
        return "E|" + super.save() + "|" + time;
    }
}
