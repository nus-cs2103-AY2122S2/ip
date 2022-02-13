package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Task type: Deadline
 *
 * Date and Time input format: eg. "/by 2/12/2019 1800"
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    private String stringTime;

    public Deadline(String input) {
        super(input.substring(9, input.indexOf("/")));
        this.stringTime = input.substring(input.indexOf("/") + 4);
        String[] tokens = stringTime.split(" ");
        this.date = LocalDate.parse(tokens[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
        this.time = LocalTime.parse(tokens[1], DateTimeFormatter.ofPattern("HHmm"));
    }

    public String getFullDateTime() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + this.time.format(DateTimeFormatter.ofPattern("h.mm a"));
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline currTask = (Deadline) o;
        boolean deadlinesEqual = currTask.description.equals(this.description)
                && currTask.date.equals(this.date)
                && currTask.time.equals(this.time);
        if (deadlinesEqual) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + ("(by: " + this.getFullDateTime() + ")");
    }
}
