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
    private int point;

    /**
     * Constructor to create a UnmarkCommand.
     *
     * @param point 1-based index of the task in the list.
     */
    public UnmarkCommand(int point) {
        super(CommandType.UNMARK);
        this.point = point;
    }

    /**
     * Marks the task as done, saves changes to file and returns response message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string.
     * @throws DukeException if FileIOexception thrown when saving file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.get(point - 1);
        task.unmarkAsDone();
        storage.saveToFile(taskList);
        return "OK, I've marked this task as not done yet:\n " + task;
    }
}
