package istjbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description, LocalDate.parse(by));
    }

    private String dateToString() {
        String formattedDate = this.date.orElseThrow().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    @Override
    public String toTxtString() {
        String marked = this.isDone? "1" : "0";
        String txtString = "deadline / " + marked + " / " + this.description + " / "
                + this.date.orElseThrow().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return txtString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateToString() + ")";
    }
}
