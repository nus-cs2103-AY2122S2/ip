package narcibot;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String time;

    /**
     * Constructor for a deadline task.
     * @param name name of the deadline
     * @param time time of the deadline
     */
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

    /**
     * Constructor for a deadline task with indication of the status of the task
     * @param name name of the deadline
     * @param time time of the deadline
     * @param done status of the deadline
     */

    public Deadline(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    /**
     * Gets the status of the deadline
     * @return a String with the format of [D][status] name (by: time)
     */
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() +" (by: " + time +")";
    }

    /**
     * Mark the deadline as done.
     */
    @Override
    public void markDone() {
        System.out.print("[D]");
        super.markDone();
        System.out.println(" (by: " + time +")");
    }

    /**
     * Mark the deadline as not done.
     */
    @Override
    public void markNotDone() {
        System.out.print("[D]");
        super.markNotDone();
        System.out.println(" (by: " + time +")");
    }

    /**
     * Write down the format on the save file;
     * @return String with the format
     */
    @Override
    public String save() {
        return "D|" + super.save() +"|" + time;
    }
}
