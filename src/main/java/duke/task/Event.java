package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String DEFAULT_DATE_FORMAT = "dd MMMM yyyy HHmm";
    private static final String SAVE_FILE_DATE_FORMAT = "d/MM/yyyy HHmm";
    private final LocalDateTime eventAt;

    public Event(String title, LocalDateTime eventAt) {
        super(title);
        this.eventAt = eventAt;
    }

    @Override
    public String getSaveFormat() throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(SAVE_FILE_DATE_FORMAT);
        return String.format("%s | %s", super.getSaveFormat(), this.eventAt.format(format));
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return String.format("[E]%s (at: %s)", super.toString(), this.eventAt.format(format));
    }
}
