import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public String deadline;
    public LocalDate date;
    public String time;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        String[] datetime = deadline.trim().split(" ");
        this.date = LocalDate.parse(datetime[0].trim());
        this.time = datetime[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + this.time + ")";
    }
}
