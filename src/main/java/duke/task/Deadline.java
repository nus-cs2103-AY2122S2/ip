package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Deadline extends Task {
    private static final String DEFAULT_DATE_FORMAT = "dd MMMM yyyy HHmm";
    private static final String SAVE_FILE_DATE_FORMAT = "d/MM/yyyy HHmm";
    private final LocalDateTime dueBy;

    public Deadline(String title, LocalDateTime dueBy) {
        super(title);
        this.dueBy = dueBy;
    }

    @Override
    public String getSaveFormat() throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(SAVE_FILE_DATE_FORMAT);
        return String.format("%s | %s", super.getSaveFormat(), this.dueBy.format(format));
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy.format(format));
    }
}
