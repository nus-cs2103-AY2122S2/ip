package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredList = tasks.filter(keyword);

        if (filteredList.isEmpty()) {
            return "There are no matching tasks in your list!";
        } else {
            return "Here are the matching tasks I've found in your list:\n" + filteredList;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
