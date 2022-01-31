package heylo.tasks;

import heylo.util.DateFormatter;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate duration;

    public Event(String description, String duration) {
        super(description);
        try {
            this.duration = LocalDate.parse(duration);
        } catch (Exception e) {
            this.duration = null;
        }
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + "\t (at " + DateFormatter.formatDateInLongForm(duration) + ")";
    }
}