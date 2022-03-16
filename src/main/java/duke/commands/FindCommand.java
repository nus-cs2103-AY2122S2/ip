package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class which handles finding of tasks in list using a keyword
 */
public class FindCommand extends Command {
    private TaskList tasks;
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
     * executes the find command
     * Finds all tasks that contain the keyword
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
        TaskList temp = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDetail().contains(keyword)) {
                temp.add(tasks.get(i));
            }
        }

        if (temp.size() == 0) {
            output = ui.findMessage(false);
        } else {
            output = ui.findMessage(true);
            for (int i = 0; i < temp.size(); i++) {
                output = output + INDENT + (i + 1) + "." + temp.get(i).toString() + "\n";
            }
        }
        return output;
    }
}
