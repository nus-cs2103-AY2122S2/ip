package tasks;

import exceptions.InvalidOperationException;

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
    public void mark() throws InvalidOperationException {
        if (this.isDone) {
            throw new InvalidOperationException("marked");
        }
        this.isDone = true;
    }

    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.isDone) {
            throw new InvalidOperationException("unmarked");
        }
        this.isDone = false;

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
