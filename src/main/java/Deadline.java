import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate date;
    protected LocalTime time;
    private DateTimeFormatter dateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("HHmm");

    public Deadline(String name, LocalDate date, LocalTime time) {
        super("D", name, date, time);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(dateOutputFormatter) + " " +
                time.format(timeOutputFormatter) + ")";
    }
}
