import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String by) {
        super(description);
        String[] time = by.split("/");
        LocalDate deadline = LocalDate.parse(time[2] + "-" + time[1] + "-" + time[0]);
        this.by = deadline;
    }

    @Override
    public String toString() {
        if (by != null) {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        } else {
            return "[D]" + super.toString();
        }
    }
}
