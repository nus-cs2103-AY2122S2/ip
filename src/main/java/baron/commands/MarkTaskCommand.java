package baron.commands;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.tasks.TaskManager;
import baron.util.Storage;

/**
 * Represents a mark task command that marks the task during execution.
 */
public class MarkTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArg;
    private final Storage storage;

    /**
     * Constructs a mark task command with the specified {@code TaskManager}, {@code Storage}
     * and command argument.
     *
     * @param taskManager the {@code TaskManager} for the command execution.
     * @param storage the {@code Storage} for the command execution.
     * @param commandArg the command argument to be parsed.
     * @see TaskManager
     * @see Storage
     */
    public MarkTaskCommand(TaskManager taskManager, Storage storage, String commandArg) {
        this.taskManager = taskManager;
        this.storage = storage;
        this.commandArg = commandArg;
    }

    /**
     * Executes the mark task command by marking the task using {@code TaskManager} and saving
     * it in {@code Storage} if necessary.
     *
     * @return the mark task command output.
     */
    @Override
    public String execute() {
        int index;
        try {
            index = CommandParser.parseTaskIntArg(this.commandArg);
            if (this.taskManager.markTask(index)) {
                try {
                    this.storage.save(this.taskManager.getAllTasks());
                } catch (BaronException e) {
                    this.taskManager.unmarkTask(index);
                    throw e;
                }
            }
            return Message.MESSAGE_MARK_SUCCESS + this.taskManager.getTask(index).toString();
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
