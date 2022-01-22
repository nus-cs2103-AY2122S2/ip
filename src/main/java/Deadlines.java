import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task{
    String time;
    LocalDate date;

    Deadlines(String description, String time) {
        super(description);
        this.time = time;
        try {
            date = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    public void print() {
        System.out.print("[D]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        if (date == null) {
            System.out.println(" (by: " + this.time + ")");
        } else {
            System.out.println(" (by: " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")");
        }
    }
}
