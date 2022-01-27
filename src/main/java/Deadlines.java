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

    public Deadlines(int mark, String description, String timeFrame) {
        super(description, mark);
        this.timeFrame = timeFrame;
    }

    public String getDeadline() {
        return "[D]" + this.getTask() + "(by: " + this.date + ", " + this.time + ")\n";
    }

    public String getFormattedText() {
        return "D:" + this.getMark() + ":" + this.getDescription() + ":" + this.timeFrame;
    }
}