package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class which handles listing of tasks in list
 */
public class ListCommand extends Command {
    private TaskList tasks;

    public ListCommand() {}

    /**
     * returns the modified task list after command execution
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
     * executes the list command
     * Lists out all tasks stored in the task list
     *
     * @param tasks tasks list to be modified
     * @param ui to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        this.tasks = tasks;
        String output;
        output = ui.listMessage();
        for (int i = 0; i < tasks.size(); i++) {
            output = output + INDENT + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }

        return output;
    }
}
