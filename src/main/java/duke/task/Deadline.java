package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    LocalDate date;


    public Deadline(String activity, String by) {
        super(activity, "D");
        date = LocalDate.parse(by.trim());
    }
    @Override
    public String printTask() {
        if (this.status) {
            return "[" + type + "][X] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[" + type + "][ ] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }

    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|" + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "|\n";
    }

}
