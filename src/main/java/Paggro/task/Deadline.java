import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    NotableDate date;
    LocalTime time;

    public Deadline(String des, NotableDate date, boolean isDone) {
        super(des, TaskType.DEADLINE, isDone);
        this.date = date;
    }

    public Deadline(String des, NotableDate date, LocalTime time, boolean isDone) {
        super(des, TaskType.DEADLINE, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String parseTask() {
        DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
        if (time == null) {
            return "D | " + Boolean.toString(isDone) + " | " + description + " | " + date.localDate.format(dFormat);
        } else {
            return "D | " + Boolean.toString(isDone) + " | " + description + " | " + date.localDate.format(dFormat)
                    + " | " + time.format(tFormat);
        }
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
