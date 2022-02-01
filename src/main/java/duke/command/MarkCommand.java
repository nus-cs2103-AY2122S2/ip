package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;

/**
 * Represents a command that will either mark a task as completed or incomplete upon execution.
 */
public class MarkCommand extends Command {
    private boolean isMark;
    private int taskNo;

    /**
     * A constructor to store the index of the task to mark
     * and to determine if to mark as complete or incomplete.
     *
     * @param isMark True indicates to mark as complete, false to indicate to mark as incomplete.
     * @param taskNo The index of the task to mark.
     */
    public MarkCommand(boolean isMark, int taskNo) {
        super();
        this.isMark = isMark;
        this.taskNo = taskNo;
    }

    /**
     * Executes the command by marking the specified task as complete or incomplete.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String response = "";
        if (isMark) {
            taskList.markDone(taskNo);
            response += "Nice! I've marked this task as done:" + "\n";
        } else {
            taskList.markUndone(taskNo);
            response += "OK, I've marked this task as not done yet:" + "\n";
        }
        response += taskList.getTask(taskNo).toString();
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return response;
    }

    /**
     * Returns true if it is an exit command and false otherwise.
     * @return a boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
