package duke.command;

import duke.manager.Ui;
import duke.manager.TaskList;
import duke.manager.Storage;
import duke.exception.DukeException;

/**
 * Represents a command that will either mark a task as completed or incomplete upon execution.
 */
public class MarkCommand extends Command{
    private boolean isMark;
    private int taskNo;

    /**
     * A constructor to store the index of the task to mark and to determine if to mark as complete or incomplete.
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
     * @param ui An Ui object to handle user interaction.
     * @param storage A Storage object to handle saving of data.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (isMark) {
            taskList.markDone(taskNo);
            ui.print("Nice! I've marked this task as done:");
            ui.print(taskList.getTask(taskNo).toString());
        } else {
            taskList.markUndone(taskNo);
            ui.print("OK, I've marked this task as not done yet:");
            ui.print(taskList.getTask(taskNo).toString());
        }
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
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
