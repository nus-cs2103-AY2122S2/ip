package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public static String wrongFormatErrorString = "Format for deadlines: 'deadline [some task] /by [dd/mm/yyyy-hh:mm]'";
    public DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    public DateTimeFormatter niceFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Task.TaskType type = Task.TaskType.DEADLINE;
    public String taskName;
    public LocalDateTime deadline;

    public Deadline(String taskName, String deadline) throws DukeException {
        this.taskName = taskName;
        try {
            this.deadline = LocalDateTime.parse(deadline, inputFormat);
        } catch (DateTimeParseException err) {
            throw new DukeException(wrongFormatErrorString);
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.done ? "X" : " ", this.taskName, this.deadline.format(niceFormat));
    }

    public String exportToString() {
        return String.format("%s %s %s %s",
            this.type, this.taskName, this.done, this.deadline.format(inputFormat));
    }
}