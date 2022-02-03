package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a command handler that executes actions based on supplied arguments.
 */
public abstract class Command {
    /** Arguments supplied to the command handler. */
    protected final String args;

    /**
     * Creates a command object.
     *
     * @param args Arguments supplied to the command handler.
     */
    protected Command(String args) {
        this.args = args;
    }

    /**
     * Reads an integer from the argument string.
     *
     * @return The integer read from the argument.
     * @throws DukeIllegalArgumentException If the argument string is not in a numerical format.
     */
    protected int parseTaskNumber() throws DukeIllegalArgumentException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(this.args);
        } catch (NumberFormatException ex) {
            throw new DukeIllegalArgumentException("Task Number must be a number");
        }
        // Note that task storage uses 0-based index
        return taskIndex - 1;
    }

    /**
     * Searches the supplied task list for the task at the numerical index given in the argument string.
     *
     * @param taskList Task list to search.
     * @return Task in the task list at the given index of the argument string.
     * @throws DukeIllegalArgumentException If the index supplied is invalid.
     */
    protected Task parseSelectTask(TaskList taskList) throws DukeIllegalArgumentException {
        int taskIndex = this.parseTaskNumber();
        Task task = taskList.getTaskByIndex(taskIndex);
        if (task == null) {
            throw new DukeIllegalArgumentException("No matching task with given number");
        }
        return task;
    }

    /**
     * Translates a date string to a {@link LocalDateTime} object.
     *
     * @param dateString Date string to process.
     * @return A {@link LocalDateTime} that represents the supplied date at 00:00 hours.
     * @throws DukeIllegalArgumentException If the supplied date string is not in a valid format.
     */
    protected LocalDateTime parseDate(String dateString) throws DukeIllegalArgumentException {
        return parseDateTime(dateString + " 00:00", "dd/MM/yyyy HH:mm");
    }

    /**
     * Translates a date-time string to a {@link LocalDateTime} object.
     *
     * @param dateString Date-time string to process.
     * @return A {@link LocalDateTime} that represents the supplied date-time.
     * @throws DukeIllegalArgumentException If the supplied date-time string is not in a valid format.
     */
    protected LocalDateTime parseDateTime(String dateString) throws DukeIllegalArgumentException {
        return parseDateTime(dateString, "dd/MM/yyyy HH:mm");
    }

    /**
     * Translates a patterned string to a {@link LocalDateTime} object based on the given format.
     *
     * @param dateString Date-time string to process.
     * @param pattern Pattern format that the date-time string is in.
     * @return A {@link LocalDateTime} that represents the supplied date-time.
     * @throws DukeIllegalArgumentException If the supplied date-time string is not in the given pattern.
     */
    protected LocalDateTime parseDateTime(String dateString, String pattern)
            throws DukeIllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeIllegalArgumentException("Date not in the format " + pattern);
        }
    }

    /**
     * Parses the arguments supplied and executes the actions relevant to the command.
     *
     * @param linePrinter {@link Printable} object that the command executor can output to.
     * @param taskList Current list of tasks that the command executor updates.
     * @return A boolean indicating if the application should continue execution.
     * @throws DukeException If an error is encountered while running the command.
     */
    public abstract boolean execute(Printable linePrinter, TaskList taskList) throws DukeException;
}
