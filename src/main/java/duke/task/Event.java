package duke.task;

import duke.ui.Ui;

import java.time.LocalDateTime;

public class Event extends Task {
    public Event(String content, LocalDateTime date) {
        super(content, date);
    }

    public Event(String content, LocalDateTime date, boolean isDone) {
        super(content, date, isDone);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[E][X] " + getContent() + " (at: " + date
                    .format(Ui.formatter) + ")";
        } else {
            return "[E][ ] " + getContent() + " (at: " + date
                    .format(Ui.formatter) + ")";
        }
    }
}
