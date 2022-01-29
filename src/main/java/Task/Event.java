package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    boolean useLocalDate = false;
    protected LocalDate at;
    protected String strAt = "";

    public Event(String description, String strAt) {
        super(description);
        strAt = strAt.trim();
        try {
            this.at = LocalDate.parse(strAt);
            useLocalDate = true;
        } catch (DateTimeParseException e){
            this.strAt = strAt;
        }
    }

    @Override
    public String saveString() {
        return "E" + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + at;
    }

    @Override
    public String toString() {
        if (useLocalDate) {
            return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + strAt + ")";
        }
    }
}