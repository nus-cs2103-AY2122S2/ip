import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter niceFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public LocalDateTime deadline;
    public Duke.TaskType type = Duke.TaskType.DEADLINE;

    public Deadline(String taskName, String deadline) throws DukeException {
        super(taskName);
        try {
            this.deadline = LocalDateTime.parse(deadline, inputFormat);
        } catch (DateTimeParseException err) {
            throw new DukeException(
                "Datetime format is wrong! Format for deadlines: 'deadline [some task] /by [dd/mm/yyyy hh:mm]'");
        }

    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.done ? "X" : " ", this.taskName, this.deadline.format(niceFormat));
    }
}
