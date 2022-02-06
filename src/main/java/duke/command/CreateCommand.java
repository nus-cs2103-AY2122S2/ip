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
        } else {
            assert false : "Trying to create invalid task type";
            throw new DukeIllegalArgumentException("Unknown task type to be created");
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
        // Check for invalid task name
        validateTaskDescription(this.args);

        assert args.length() > 0;
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
        // Check for syntax correctness by delimiter
        final String[] argParts = this.parseDelimitedCommand(this.args, " /by ");
        final String taskDescription = validateTaskDescription(argParts[0]);
        final LocalDateTime taskBy = parseDateTime(argParts[1]);
        assert taskBy != null;

        final Task task = taskList.addTask(new Deadline(taskDescription, taskBy));
        assert task != null;
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
        // Check for syntax correctness by delimiter
        final String[] argParts = this.parseDelimitedCommand(this.args, " /at ");
        final String taskDescription = validateTaskDescription(argParts[0]);
        final LocalDateTime taskAt = parseDateTime(argParts[1]);
        assert taskAt != null;

        final Task task = taskList.addTask(new Event(taskDescription, taskAt));
        assert task != null;
        this.printResponse(linePrinter, task, taskList, "Event");
    }

    /**
     * Validates a 2-part command separated by a delimiter and returns the parts if command is valid.
     *
     * @param command The 2-part command to be validated.
     * @param delimiter The phrase separating the 2 parts of the command.
     * @return The 2 parts of the command in a String array.
     * @throws DukeIllegalArgumentException If the command is not in a valid format.
     */
    private String[] parseDelimitedCommand(String command, String delimiter)
            throws DukeIllegalArgumentException {
        final String[] parts = command.split(delimiter);
        if (parts.length < 2) {
            throw new DukeIllegalArgumentException(
                    String.format("Not in the format <Task name>%s<Date>", delimiter));
        }
        return parts;
    }

    /**
     * Validates that the task description passed as an argument is not empty.
     *
     * @param taskDescription Task description to validate.
     * @return The task description passed in as an argument.
     * @throws DukeIllegalArgumentException If the task description is empty.
     */
    private String validateTaskDescription(String taskDescription) throws DukeIllegalArgumentException {
        if (taskDescription.equals("")) {
            throw new DukeIllegalArgumentException("Task description cannot be empty");
        }
        assert taskDescription.length() > 0;
        return taskDescription;
    }

    private void printResponse(Printable linePrinter, Task task, TaskList taskList, String type) {
        linePrinter.print(String.format("Added the following %s Task:", type));
        linePrinter.print(String.format("\t%s", task.getReadableString()));
        linePrinter.print(String.format("Now you have %d task(s) in the list", taskList.getTaskCount()));
    }
}
