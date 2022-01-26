package duke.task;

import duke.ui.Ui;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public Deadline(String content, LocalDateTime date) {
        super(content, date);
    }

    public Deadline(String content, LocalDateTime date, boolean isDone) {
        super(content, date, isDone);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[D][X] " + getContent() + " (by: " + date
                    .format(Ui.formatter) + ")";
        } else {
            return "[D][ ] " + getContent() + " (by: " + date
                    .format(Ui.formatter) + ")";
        }
    }
}
