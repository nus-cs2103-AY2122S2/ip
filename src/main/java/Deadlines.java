import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private final String time;
    private final String date;

    public Deadlines(String description, String date, String time) {
        super(description);
        this.time = time;
        this.date = date;
    }

    public String getDeadline() {
        return "[D]" + this.getTask() + "(by: " + this.date + ", " + this.time + ")\n";
    }
}