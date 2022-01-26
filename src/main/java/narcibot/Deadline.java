package narcibot;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
        try {
            LocalDate date = LocalDate.parse(time);
            System.out.println("[D][ ] " + name + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        } catch (DateTimeParseException e) {
            System.out.println("[D][ ] " + name + " (by: " + time + ")");
        }
    }

    public Deadline(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() +" (by: " + time +")";
    }

    @Override
    public void markDone() {
        System.out.print("[D]");
        super.markDone();
        System.out.println(" (by: " + time +")");
    }

    @Override
    public void markNotDone() {
        System.out.print("[D]");
        super.markNotDone();
        System.out.println(" (by: " + time +")");
    }

    @Override
    public String save() {
        return "D|" + super.save() +"|" + time;
    }
}
