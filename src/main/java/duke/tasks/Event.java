package duke.tasks;

import duke.exceptions.InvalidOperationException;

import java.time.LocalDate;

public class Event extends Task {
    private String description;
    private LocalDate date;
    private boolean isDone;

    public Event(String details, String date) {
        this.description = details;
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
            return "[E]"+"[X] " + description + "(at: "
                    + date.getDayOfMonth()
                    + " "
                    + date.getMonth()
                    + " "
                    + date.getYear()
                    + ")";
        }
        else {
            return "[E]"+"[ ] " + description + "(at: "
                    + date.getDayOfMonth()
                    + " "
                    + date.getMonth()
                    + " "
                    + date.getYear()
                    + ")";
        }
    }
}
