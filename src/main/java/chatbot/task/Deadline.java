package chatbot.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    private final LocalDate date;
    private final LocalTime time;

    public Deadline(String desc, LocalDate date, LocalTime time) {
        super(desc);
        this.date = date;
        this.time = time;
    }

    @Override public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + " " + time.toString() + ")";
    }
}