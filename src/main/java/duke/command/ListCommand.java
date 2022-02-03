package src.main.java.duke.command;

import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
