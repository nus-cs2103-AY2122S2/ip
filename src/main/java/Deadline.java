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

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        String[] time = by.split("/");
        LocalDate deadline = LocalDate.parse(time[2] + "-" + time[1] + "-" + time[0]);
        this.by = deadline;
    }

    public String getTaskData() {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        if (by != null) {
            return "D " + isDone + " " + this.description + "/by " + this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";
        } else {
            return "D " + isDone + " " + this.description + "\n";
        }
    }

    @Override
    public String toString() {
        if (by != null) {
            return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        } else {
            return "[D]" + super.toString();
        }
    }
}
