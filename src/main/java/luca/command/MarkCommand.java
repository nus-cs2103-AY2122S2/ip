package luca.command;

import luca.common.DukeException;
import luca.storage.Storage;
import luca.task.Task;
import luca.task.TaskList;

/**
 * Responsible for the functionality needed when marking a task.
 */
public class MarkCommand extends Command {

    /** 1-based index of the task in the list. */
    private int pointer;

    /**
     * Constructor to create a Mark Command.
     *
     * @param pointer 1-based index of the task in the list.
     */
    public MarkCommand(int pointer) {
        super(CommandType.MARK);
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
        Task task = taskList.markTaskAsDone(pointer);
        storage.saveToFile(taskList);
        return "Great! I have marked this task as done:\n " + task;
    }
}
