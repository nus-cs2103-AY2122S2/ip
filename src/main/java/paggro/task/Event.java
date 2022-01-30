package paggro.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import paggro.notableDate.NotableDate;

public class Event extends Task {
    public NotableDate date;
    LocalTime time;

    public Event(String des, NotableDate date, boolean isDone) {
        super(des, isDone);
        this.date = date;
    }

    public Event(String des, NotableDate date, LocalTime time, boolean isDone) {
        super(des, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String parseTask() {
        DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
        if (time == null) {
            return "E | " + Boolean.toString(isDone) + " | " + description + " | " + date.localDate.format(dFormat);
        } else {
            return "E | " + Boolean.toString(isDone) + " | " + description + " | " + date.localDate.format(dFormat)
                    + " | " + time.format(tFormat);
        }
    }

    @Override
    public String toString() {
        String str;
        if (isDone) {
            str = "[E][X] " + description + " (at: " + date;
        } else {
            str = "[E][ ] " + description + " (at: " + date;
        }

        if (time != null) {
            str += " " + time.format(DateTimeFormatter.ofPattern("h:mma"));
        }
        str += ")";

        return str;
    }
}