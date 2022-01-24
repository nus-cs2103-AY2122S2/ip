package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected Boolean useLocalDate = false;
    protected LocalDate by;
    protected String strBy;

    public Deadline(String description, String strBy) {
        super(description);
        strBy = strBy.trim();
        try {
            this.by = LocalDate.parse(strBy);
            useLocalDate = true;
        } catch (DateTimeParseException e){
            this.strBy = strBy;
        }
    }

    @Override
    public String saveString() {
        return "T" + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + by;
    }

    @Override
    public String toString() {
        if (useLocalDate) {
            return "[D]" +  super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" +  super.toString() + " (by: " + strBy + ")";
        }
    }
}