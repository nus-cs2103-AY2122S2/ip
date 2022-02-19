package duke;

import java.util.ArrayList;

/**
 * Class that represents finding a task by keyword in the form of a command.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        assert(keyword != null && !keyword.equals(""));
        this.keyword = keyword;
    }

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

    @Override
    public boolean isEnd() {
        return false;
    }
}
