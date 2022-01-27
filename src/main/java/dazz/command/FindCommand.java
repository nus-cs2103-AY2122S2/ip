package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

public class FindCommand extends Command {
    private final String search;
    public FindCommand(String description) {
        this.search = description;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showSearches(taskList, search);
    }
}
