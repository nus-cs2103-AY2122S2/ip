package tasks;
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
    public void mark() {
        if (!this.done) {
            this.done = true;
        }

    }

    @Override
    public void unmark() {
        if (this.done) {
            this.done = false;
        }

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
