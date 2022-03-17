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
     * returns the modified task list after command
     *
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * returns true boolean if command execution ends program
     *
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * executes the UnMark command
     * Marks the task stored at index as not done
     * 
     * @param tasks tasks list to be modified
     * @param ui to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        assert tasks != null;
        this.tasks = tasks;
        if (pos >= tasks.size()) {
            throw new OutOfRangeException();
        }
        Task unmarked = tasks.get(pos);
        unmarked.unmark();
        tasks.set(pos, unmarked);
        storage.saveFile(this.tasks);
        return  ui.unMarkMessage(tasks.get(pos).toString());
    }
}
