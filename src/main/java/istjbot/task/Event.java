package istjbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    Event(String description, String at) throws DateTimeParseException {
        super(description, LocalDate.parse(at));
    }

    private String dateToString() {
        String formattedDate = this.date.orElseThrow().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    @Override
    public String toTxtString() {
        String marked = this.isDone? "1" : "0";
        String txtString = "event / " + marked + " / " + this.description + " / "
                + this.date.orElseThrow().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return txtString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateToString() + ")";
    }
}
