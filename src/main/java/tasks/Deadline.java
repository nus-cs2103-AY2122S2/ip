package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class that functions as an abstraction of a deadline task. */
public class Deadline extends Task {

    public static String wrongFormatErrorString = "Format for deadlines: 'deadline [some task] /by [dd/mm/yyyy-hh:mm]'";
    public DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    public DateTimeFormatter niceFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Task.TaskType type = Task.TaskType.DEADLINE;
    public String taskName;
    public LocalDateTime deadline;

    /**
     * Constructor method for the Deadline task.
     *
     * @param taskName Name of the Deadline task.
     * @param deadline The date and time that the Deadline must be completed by.
     * @throws DukeException If the date and time argument is not formatted properly.
     */
    public Deadline(String taskName, String deadline) throws DukeException {
        this.taskName = taskName;
        try {
            this.deadline = LocalDateTime.parse(deadline, inputFormat);
        } catch (DateTimeParseException err) {
            throw new DukeException(wrongFormatErrorString);
        }
    }

    /**
     * The string representation of the task to be displayed to the user.
     *
     * @return A string representation of the task for the user.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.done ? "X" : " ", this.taskName, this.deadline.format(niceFormat));
    }

    /**
     * Converts the task into a string representation to be used to be saved on disk.
     *
     * @return The task in string format to be used to be saved on disk.
     */
    public String exportToString() {
        return String.format("%s %s %s %s",
            this.type, this.taskName, this.done, this.deadline.format(inputFormat));
    }
}