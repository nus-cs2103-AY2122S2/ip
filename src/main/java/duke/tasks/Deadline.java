package duke.tasks;
import duke.exceptions.InvalidOperationException;

import java.time.LocalDate;

public class Deadline extends Task {
    private String item;
    private LocalDate date;
    private boolean done;

    public Deadline(String details, String date) {
        this.item = details;
        date = date.stripLeading();
        this.date = LocalDate.parse(date);
    }

    @Override
    public void mark() throws InvalidOperationException {
        if (this.done) {
            throw new InvalidOperationException("marked");
        }
        this.done = true;

    }

    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.done) {
            throw new InvalidOperationException("unmarked");
        }
        this.done = false;

    }

    @Override
    public String toString() {
        if (done) {
            return "[D]"+"[X] " + item + "(by: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
        else {
            return "[D]"+"[ ] "+ item + "(by: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
    }
}
