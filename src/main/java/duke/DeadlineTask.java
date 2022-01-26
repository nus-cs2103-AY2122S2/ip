package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {

    private final LocalDate date;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public DeadlineTask(String name, String date) throws DukeException {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date!");
        }
    }

    @Override
    public String toString() {
        String dateOutput = String.format("(by: %s)", date.format(pattern));
        return String.format("[D][%s] %s %s", getStatusIcon(), name, dateOutput);
    }

    @Override
    public String toStore() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.name, this.date);
    }
}
