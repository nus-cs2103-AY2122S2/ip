package pac.command;

import pac.storage.Storage;
import pac.task.TaskList;
import pac.ui.Ui;

import java.io.IOException;

public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList matchingTasks = tasks.find(keyword);
        return ui.showFind(matchingTasks);
    }
}
