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
 * Represents a command that deletes task from the list.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class DeleteCommand implements Command {
    private static final int COMMAND_LENGTH = 6;
    private static final String MESSAGE = "Got it! I have deleted this task:";

    private final int index;

    /**
     * Creates an delete command.
     *
     * @param input User input.
     * @throws UltoiException If the input cannot be identified.
     */
    public DeleteCommand(String input) throws UltoiException {
        try {
            this.index = Integer.parseInt(input.substring(this.COMMAND_LENGTH + 1)) - 1;
        } catch (Exception e) {
            throw UltoiException.commandMismatchException();
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
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
        Task deletedTask;

        try {
            deletedTask = tasks.deleteTask(this.index);
        } catch (Exception e) {
            throw UltoiException.indexOutOfBoundException();
        }

        storage.save(tasks);
        return ui.showMsg(generateMsg(deletedTask, tasks));
    }

    /**
     * Generates a message for the command.
     *
     * @param deletedTask Task deleted.
     * @param tasks Task list.
     * @return Message for the command.
     */
    private String generateMsg(Task deletedTask, TaskList tasks) {
        return this.MESSAGE + "\n"
                + UltoiUi.INDENT + deletedTask.toString() + "\n"
                + tasks.generateNumOfTasksMsg();
    }
}
