package duke;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deadline extends Task {
    private String deadlineDate;
    private LocalDate date;

    Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(deadlineDate);
            this.date = DateParser.parseDate(deadlineDate);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    Deadline(String description, String deadlineDate, Boolean completed) {
        super(description, completed);
        this.deadlineDate = deadlineDate;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(deadlineDate);
            this.date = DateParser.parseDate(deadlineDate);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    public String getTime() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        String printedDate;
        if (date != null) {
            printedDate = DateParser.dateToString(this.date);
        } else {
            printedDate = this.deadlineDate;
        }
        return "[D]" + super.toString() + " (by: " + printedDate + ")";
    }
}
