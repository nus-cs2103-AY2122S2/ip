import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    protected String time;
    public boolean isDone;

    public Deadline(String description, LocalDate by, String time) {
        super(description);
        this.by = by;
        this.time = time;
        this.isDone = false;
    }

    public Deadline(String description, LocalDate by, String time, boolean isDone) {
        super(description);
        this.by = by;
        this.time = time;
        this.isDone = isDone;
    }

    public String toSave() {
        int isDoneNumber;
        if(isDone) {
            isDoneNumber = 1;
        } else {
            isDoneNumber = 0;
        }
        return "D | " + isDoneNumber + " | " + description +
                " | " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + time + System.lineSeparator();
    }

    @Override
    public String toString() {
     return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +" " + time + ")";
    }
}
