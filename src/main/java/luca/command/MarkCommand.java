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
    private int point;

    /**
     * Constructor to create a Mark Command.
     *
     * @param point 1-based index of the task in the list.
     */
    public MarkCommand(int point) {
        super(CommandType.MARK);
        this.point = point;
    }

    /**
     * Marks the task as done, saves changes to file and returns response message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string.
     * @throws DukeException if File IO exception is thrown when saving file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.get(point - 1);
        task.markAsDone();
        storage.saveToFile(taskList);
        return "Great! I have marked this task as done:\n " + task;
    }
}
