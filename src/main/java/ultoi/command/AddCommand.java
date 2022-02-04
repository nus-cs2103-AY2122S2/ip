package ultoi.command;

import ultoi.task.Task;
import ultoi.task.ToDo;
import ultoi.task.Deadline;
import ultoi.task.Event;

import ultoi.util.Ultoi;
import ultoi.util.UltoiUi;
import ultoi.util.UltoiException;
import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.Parser;
import ultoi.util.DateTime;

/**
 * Represents a command that adds task to the list.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class AddCommand implements Command {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    public static final String COMMAND_BY = "/by";
    public static final String COMMAND_AT = "/at";
    private static final String MESSAGE = "Got it! I have added this tasks:";

    private final Task task;

    /**
     * Creates an add command.
     *
     * @param input User input.
     * @throws UltoiException If the input cannot be identified.
     */
    public AddCommand(String input) throws UltoiException {
        try {
            if (input.startsWith(this.COMMAND_TODO)) {
                this.task = new ToDo(input.substring(COMMAND_TODO.length() + 1));
            } else if (input.startsWith(this.COMMAND_DEADLINE)) {
                this.task = Parser.parseDeadline(input);
            } else if (input.startsWith(this.COMMAND_EVENT)) {
                this.task = Parser.parseEvent(input);
            } else {
                throw UltoiException.noSuchCommandException();
            }
        } catch (UltoiException e) {
            throw e;
        }
    }

    /**
     * Executes the command.
     *
     * @param ui User interface to be used.
     * @param tasks Task list to be used.
     * @param storage Storage to be used.
     * @throws UltoiException If any Ultoi exception happens.
     */
    @Override
    public void execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
        tasks.addTask(this.task);
        storage.save(tasks);
        ui.showMsg(generateMsg(tasks));
        return;
    }

    /**
     * Generates a message for the command.
     *
     * @param tasks Task list used.
     * @return Message for the comment.
     */
    private String generateMsg(TaskList tasks) {
        return this.MESSAGE + "\n"
                + UltoiUi.INDENT + this.task.toString() + "\n"
                + tasks.generateNumOfTasksMsg();
    }

    /**
     * Returns the task.
     *
     * @return Task according to the command.
     */
    public Task getTask() {
        return this.task;
    }
}
