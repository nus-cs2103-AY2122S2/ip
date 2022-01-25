package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.search(query);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
