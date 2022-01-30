package saitama.commands;

import java.util.List;

import saitama.storage.Storage;
import saitama.tasks.TaskList;
import saitama.ui.Ui;
import saitama.tasks.Task;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("OK...");
        List<Task> matchingTasks = taskList.search(query);
        return ui.showMatchingTasks(matchingTasks);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
