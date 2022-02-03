package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;
import duke.util.Printable;

/**
 * Represents a handler for the todo, deadline and event commands.
 * Encapsulates the creation logic for all available types of tasks.
 */
public class CreateCommand extends Command {
    private final TaskType taskType;

    /**
     * Creates a handler for a creation command.
     *
     * @param args Arguments supplied to the command handler.
     * @param taskType The type of task to be created.
     */
    CreateCommand(String args, TaskType taskType) {
        super(args);
        this.taskType = taskType;
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        if (this.taskType == TaskType.TODO) {
            this.parseCreateTodo(linePrinter, taskList);
        } else if (this.taskType == TaskType.DEADLINE) {
            this.parseCreateDeadline(linePrinter, taskList);
        } else if (this.taskType == TaskType.EVENT) {
            this.parseCreateEvent(linePrinter, taskList);
        }

        return true;
    }

    /**
     * Parses and creates a Todo object.
     *
     * @param linePrinter Object that the command handler outputs responses to.
     * @param taskList Current task list that the command handler should add a new task to.
     * @throws DukeIllegalArgumentException If the argument string is empty.
     */
    private void parseCreateTodo(Printable linePrinter, TaskList taskList)
            throws DukeIllegalArgumentException {
        // Syntax Check
        if (args.equals("")) {
            throw new DukeIllegalArgumentException("Task name cannot be empty");
        }

        final Task task = taskList.addTask(new Todo(this.args));
        this.printResponse(linePrinter, task, taskList, "Todo");
    }

    /**
     * Parses and creates a Deadline object.
     *
     * @param linePrinter Object that the command handler outputs responses to.
     * @param taskList Current task list that the command handler should add a new task to.
     * @throws DukeIllegalArgumentException If the argument string does not follow the expected format.
     */
    private void parseCreateDeadline(Printable linePrinter, TaskList taskList)
            throws DukeIllegalArgumentException {
        // Syntax Check
        final String[] argParts = this.args.split(" /by ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <Task name> /by <Date>");
        }

        final String taskDescription = argParts[0];
        if (taskDescription.equals("")) {
            throw new DukeIllegalArgumentException("Task name cannot be empty");
        }
        final LocalDateTime taskBy = parseDateTime(argParts[1]);
        final Task task = taskList.addTask(new Deadline(taskDescription, taskBy));
        this.printResponse(linePrinter, task, taskList, "Deadline");
    }

    /**
     * Parses and creates a Event object.
     *
     * @param linePrinter Object that the command handler outputs responses to.
     * @param taskList Current task list that the command handler should add a new task to.
     * @throws DukeIllegalArgumentException If the argument string does not follow the expected format.
     */
    private void parseCreateEvent(Printable linePrinter, TaskList taskList)
            throws DukeIllegalArgumentException {
        // Syntax Check
        final String[] argParts = this.args.split(" /at ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <Task name> /at <Date>");
        }

        final String taskDescription = argParts[0];
        if (taskDescription.equals("")) {
            throw new DukeIllegalArgumentException("Task name cannot be empty");
        }
        final LocalDateTime taskAt = parseDateTime(argParts[1]);
        final Task task = taskList.addTask(new Event(taskDescription, taskAt));
        this.printResponse(linePrinter, task, taskList, "Event");
    }

    private void printResponse(Printable linePrinter, Task task, TaskList taskList, String type) {
        linePrinter.print(String.format("Added the following %s Task:", type));
        linePrinter.print(String.format("\t%s", task.getReadableString()));
        linePrinter.print(String.format("Now you have %d task(s) in the list", taskList.getTaskCount()));
    }
}
