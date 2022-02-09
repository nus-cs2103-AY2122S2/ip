package luca.command;

import luca.common.DukeException;
import luca.storage.Storage;
import luca.task.Task;
import luca.task.TaskList;

/**
 * Responsible for the functionality needed when un-marking a Task.
 */
public class UnmarkCommand extends Command {

    /** 1-based index of the task in the list. */
    private int pointer;

    /**
     * Constructor to create a UnmarkCommand.
     *
     * @param pointer 1-based index of the task in the list.
     */
    public UnmarkCommand(int pointer) {
        super(CommandType.UNMARK);
        this.pointer = pointer;
    }

    /**
     * Marks the task as done, saves changes to file and returns response message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string.
     * @throws DukeException if I/O error or Invalid argument.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.unmarkTaskAsDone(pointer);;
        storage.saveToFile(taskList);
        return "OK, I've marked this task as not done yet:\n " + task;
    }
}
