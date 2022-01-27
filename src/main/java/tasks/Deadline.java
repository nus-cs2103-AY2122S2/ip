package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class that functions as an abstraction of a deadline task. */
public class Deadline extends Task {

    public static String WRONG_FORMAT_ERROR_STRING =
            "Format for deadlines: 'deadline [some task] /by [dd/mm/yyyy-hh:mm]'";
    public DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    public DateTimeFormatter PRETTY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Task.TaskType taskType = Task.TaskType.DEADLINE;
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
            this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException err) {
            throw new DukeException(WRONG_FORMAT_ERROR_STRING);
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
                this.isDone() ? "X" : " ", this.taskName, this.deadline.format(PRETTY_FORMAT));
    }

    /**
     * Converts the task into a string representation to be used to be saved on disk.
     *
     * @return The task in string format to be used to be saved on disk.
     */
    public String exportToString() {
        return String.format("%s %s %s %s",
            this.taskType, this.taskName, this.isDone(), this.deadline.format(INPUT_FORMAT));
    }
}