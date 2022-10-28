package luca.command;

import luca.common.DukeException;
import luca.parser.exceptions.InvalidArgumentException;
import luca.storage.Storage;
import luca.task.Task;
import luca.task.TaskList;

/**
 * Responsible for the functionality needed when deleting a task.
 */
public class DeleteCommand extends Command {

    /** 1-based index of task to be deleted. */
    private int point;

    /**
     * Constructor to create Delete Command.
     *
     * @param point 1-based index of task to be deleted.
     */
    public DeleteCommand(int point) {
        super(CommandType.DELETE);
        this.point = point;
    }

    /**
     * Deletes tasks pointed out, saves the task list and returns
     * out appropriate response message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string.
     * @throw DukeException if there is a File I/O exception or when point is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            throw new InvalidArgumentException("There are no tasks to be deleted.");
        }
        if (point > taskList.size() || point <= 0) {
            throw new InvalidArgumentException("The index given is invalid.");
        }

        Task task = taskList.remove(point - 1);
        storage.saveToFile(taskList);
        return "Noted. I've removed this task:\n " + task
                + "\n" + taskList.sizeDescription();
    }
}
