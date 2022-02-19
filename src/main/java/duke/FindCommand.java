package duke;

import java.util.ArrayList;

/**
 * Class that represents finding a task by keyword in the form of a command.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs an instance of Add Task Command.
     *
     * @param keyword Keyword to be searched.
     */
    public FindCommand(String keyword) {
        assert(keyword != null && !keyword.equals(""));
        this.keyword = keyword;
    }

    /**
     * Executes the instance of Find Command.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interaction with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTaskArr();

        String s = "Here are the matching tasks in your list:\n";

        for (int i = 0; i < t.size(); i++) {
            if (tasks.getTask(i).toString().contains(keyword)) {
                s += ui.showTask(i + 1, tasks.getTask(i)) + ' ';
            }
        }
        return s;
    }

    /**
     * Checks whether this command is the terminating command to Duke.
     *
     * @return False.
     */
    @Override
    public boolean isEnd() {
        return false;
    }
}
