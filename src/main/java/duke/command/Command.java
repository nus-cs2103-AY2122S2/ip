package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Printable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Command {
    protected final String args;

    protected Command(String args) {
        this.args = args;
    }

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

    protected Task parseSelectTask(TaskList taskList) throws DukeIllegalArgumentException {
        int taskIndex = this.parseTaskNumber();
        Task task = taskList.getTaskByIndex(taskIndex);
        if (task == null) {
            throw new DukeIllegalArgumentException("No matching task with given number");
        }
        return task;
    }

    protected LocalDateTime parseDate(String dateString) throws DukeIllegalArgumentException {
        return parseDateTime(dateString + " 00:00", "dd/MM/yyyy HH:mm");
    }

    protected LocalDateTime parseDateTime(String dateString) throws DukeIllegalArgumentException {
        return parseDateTime(dateString, "dd/MM/yyyy HH:mm");
    }

    protected LocalDateTime parseDateTime(String dateString, String pattern)
            throws DukeIllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeIllegalArgumentException("Date not in the format " + pattern);
        }
    }

    public abstract boolean execute(Printable linePrinter, TaskList taskList) throws DukeException;
}
