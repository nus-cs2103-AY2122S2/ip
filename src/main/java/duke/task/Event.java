package duke.task;

import duke.ui.Ui;

import java.time.LocalDateTime;

public class Event extends Task {
    /**
     * Instantiates a Event object with content and date.
     *
     * @param content String content.
     * @param date    LocalDateTime due date.
     */
    public Event(String content, LocalDateTime date) {
        super(content, date);
    }

    /**
     * Instantiates a Event object with content and date and isDone.
     *
     * @param content String content.
     * @param date    LocalDateTime date on which the event is occurring.
     * @param isDone  Boolean to show if the task is done.
     */
    public Event(String content, LocalDateTime date, boolean isDone) {
        super(content, date, isDone);
    }

    /**
     * Returns the String representation of this class object.
     *
     * @return String String representation of Event.
     */
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
