import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    NotableDate date;
    LocalTime time;

    public Deadline(String des, NotableDate date) {
        super(des, TaskType.DEADLINE);
        this.date = date;
    }

    public Deadline(String des, NotableDate date, LocalTime time) {
        super(des, TaskType.DEADLINE);
        this.date = date;
        this.time = time;
    }
    @Override
    public String toString() {
        String str;
        if (isDone) {
            str = "[D][X] " + description + " (by: " + date;
        } else {
            str = "[D][ ] " + description + " (by: " + date;
        }

        if (time != null) {
            str += " " + time.format(DateTimeFormatter.ofPattern("h:mma"));
        }
        str += ")";

        return str;
    }
}
