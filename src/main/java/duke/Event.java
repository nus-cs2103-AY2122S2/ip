package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class Event extends Task {

    protected LocalDate eventDate;
    protected DayOfWeek day;
    protected Month month;
    protected int year;
    protected LocalTime time;

    public Event(String description, LocalDate eventDate, LocalTime time) {
        super(description);
        this.eventDate = eventDate;
        this.day = eventDate.getDayOfWeek();
        this.month = eventDate.getMonth();
        this.year = eventDate.getYear();
        this.time = time;
    }

    @Override
    public String toStringForSave() {
        return "E "+ super.toStringForSave() + " # " + this.eventDate + " " + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")";
    }
}
