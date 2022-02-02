package duke.commands;

import duke.exceptions.OutOfRangeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class which handles un-marking of tasks in list as not done
 */
public class UnMarkCommand extends Command {
    private TaskList tasks;
    private final int pos;

    public UnMarkCommand(int pos) {
        this.pos = pos - 1;
    }

    /**
     * Method to get the modified task list after command execution
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * Method to see if command ends the main program loop
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * Method to execute the UnMark command
     * Marks the task stored at index as not done
     * @param tasks tasks list to be modified
     * @param ui to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        this.tasks = tasks;
        if (pos >= tasks.size()) {
            throw new OutOfRangeException();
        }
        Task unmarked = tasks.get(pos);
        unmarked.unmark();
        tasks.set(pos, unmarked);

        return "I've marked this task as not done yet:" + "  " + tasks.get(pos).toString();
    }
}
