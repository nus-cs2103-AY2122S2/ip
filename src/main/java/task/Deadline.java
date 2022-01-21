package task;

import parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String title,LocalDateTime by) {
        super(title);
        this.by = by;
    }

    public Deadline(String title, boolean done, LocalDateTime by) {
        super(title, done);
        this.by = by;
    }

    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.printDate(by) + ")";
    }
}
