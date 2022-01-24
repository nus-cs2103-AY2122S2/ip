package tasks;

import java.time.LocalDate;

public class Event extends Task {
    private String item;
    private LocalDate date;
    private boolean isDone;

    public Event(String details, String date) {
        this.item = details;
        date = date.stripLeading();
        this.date = LocalDate.parse(date);
    }

    @Override
    public void mark() {
        if (!this.isDone) {
            this.isDone = true;
        }

    }

    @Override
    public void unmark() {
        if (this.isDone) {
            this.isDone = false;
        }

    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E]"+"[X] " + item + "(at: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
        else {
            return "[E]"+"[ ] " + item + "(at: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
    }
}
