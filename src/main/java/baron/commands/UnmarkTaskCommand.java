package baron.commands;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.tasks.TaskManager;
import baron.util.Storage;

/**
 * Represents a unmark task command that unmarks the task during execution.
 */
public class UnmarkTaskCommand extends Command {
    private final TaskManager taskManager;
    private final Storage storage;
    private final String commandArg;

    /**
     * Constructs a unmark task command with the specified {@code TaskManager}, {@code Storage}
     * and command argument.
     *
     * @param taskManager the {@code TaskManager} for the command execution.
     * @param storage the {@code Storage} for the command execution.
     * @param commandArg the command argument to be parsed.
     * @see TaskManager
     * @see Storage
     */
    public UnmarkTaskCommand(TaskManager taskManager, Storage storage, String commandArg) {
        this.taskManager = taskManager;
        this.storage = storage;
        this.commandArg = commandArg;
    }

    /**
     * Executes the unmark task command by unmarking the task using {@code TaskManager} and saving
     * it in {@code Storage} if necessary.
     *
     * @return the unmark task command output.
     */
    @Override
    public String execute() {
        int index;
        try {
            index = CommandParser.parseTaskIntArg(this.commandArg);
            if (this.taskManager.unmarkTask(index)) {
                try {
                    this.storage.save(this.taskManager.getAllTasks());
                } catch (BaronException e) {
                    this.taskManager.markTask(index);
                    throw e;
                }
            }
            return Message.MESSAGE_UNMARK_SUCCESS + this.taskManager.getTask(index).toString();
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
