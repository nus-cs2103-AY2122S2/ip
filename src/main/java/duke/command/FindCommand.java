package src.main.java.duke.command;

import src.main.java.duke.TaskList;
import src.main.java.duke.Ui;
import src.main.java.duke.Storage;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}