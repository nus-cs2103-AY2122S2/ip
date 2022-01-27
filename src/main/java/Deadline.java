import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String date;
    private String time;

    public Deadline(String content, String deadline) {
        super(content);
        setDateAndTime(deadline);
    }

    public Deadline(String content, String deadline, boolean done) {
        super(content, done);
        setDateAndTime(deadline);
    }

    public Deadline(String content, boolean done) {
        super(content, done);
    }

    public static Deadline createDeadlineFromStorage(String content, String deadline, boolean done) {
        Deadline ddl = new Deadline(content, done);
        parseDateAndTime(ddl, deadline);
        return ddl;
    }

    public void setDateAndTime(String deadline) {
        String[] dt = deadline.split(" ");
        LocalDate date = LocalDate.parse(dt[0]);
        LocalTime time = LocalTime.parse(dt[1]);
        this.date = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        this.time = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    private static void parseDateAndTime(Deadline ddl, String deadline) {
        ddl.date = deadline.substring(0, 11);
        ddl.time = deadline.substring(12);
    }

    @Override
    public String toString() {
        return "[D]" + (super.done ? "[X] " : "[ ] ") + super.content + " (by " + this.date + " " + this.time + ")";
    }

    @Override
    public String toFileString() {
        return "D, " + (super.done ? "1, " : "0, ") + super.content + ", " + this.date + " " + this.time;
    }
}