import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task{

    private String time;
    LocalDate date;


    Events(String description, String time) {
        super(description);
        this.time = time;
        try {
            date = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    public void print() {
        System.out.print("[E]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        if (date == null) {
            System.out.println(" (at: " + this.time + ")");
        } else {
            System.out.println(" (at: " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")");
        }

    }
}
