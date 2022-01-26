package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {

    private final LocalDate date;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public EventTask(String name, String date) throws DukeException {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date!");
        }
    }

    @Override
    public String toString() {
        String dateOutput = String.format("(at: %s)", date.format(pattern));
        return String.format("[E][%s] %s %s", getStatusIcon(), name, dateOutput);
    }

    @Override
    public String toStore() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.name, this.date);
    }

}
