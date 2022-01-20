package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Event extends Task{
    private String eventTime;
    private LocalDate date;

    Event(String description, String eventTime, Boolean completed) {
        super(description, completed);
        this.eventTime = eventTime;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(eventTime);
            this.date = DateParser.parseDate(eventTime);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(eventTime);
            this.date = DateParser.parseDate(eventTime);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    @Override
    public String getType() {
        return "Event";
    }

    public String getTime() {
        return this.eventTime;
    }

    @Override
    public String toString() {
        String printedDate;
        if (date != null) {
            printedDate = DateParser.dateToString(this.date);
        } else {
            printedDate = this.eventTime;
        }
        return "[E]" + super.toString() + " (at: " + printedDate + ")";
    }
}