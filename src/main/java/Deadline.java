import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    LocalDate date;


    public Deadline(String activity, String by) {
        super(activity, "D");
        date = LocalDate.parse(by.trim());
    }
    @Override
    public String getStatus() {
        if (this.status == 0) {
            return "[" + type + "][ ] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[" + type + "][X] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "|\n";
    }
}
