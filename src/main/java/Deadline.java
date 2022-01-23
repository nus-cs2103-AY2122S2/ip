import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate date;


    public Deadline(String activity, String type, String by) {
        super(activity, type);
        date = LocalDate.parse(by.trim());
    }
    @Override
    public void getStatus() {
        if (this.status == 0) {
            System.out.println("[" + type + "][ ] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        } else {
            System.out.println("[" + type + "][X] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        }
    }
}
