package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate date;

    public Event(String taskName, String at) throws DukeException {
        super(taskName);
        boolean isCorrectFormat = formatChecker(at);
        if (isCorrectFormat) {
            try {
                this.date = LocalDate.parse(at);
            } catch (DateTimeException e) {
                throw new DukeException("Invalid date");
            }
        } else {
            throw new DukeException("Invalid time format, please use yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        String done = isComplete() ? "[X]" : "[ ]";
        return "[E]" + done + getTaskName() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public static boolean formatChecker(String by) {
        if (by.length() != 10) {
            return false;
        } else if (by.charAt(4) != '-' || by.charAt(7) != '-') {
            return false;
        } else {
            return true;
        }
    }
}
