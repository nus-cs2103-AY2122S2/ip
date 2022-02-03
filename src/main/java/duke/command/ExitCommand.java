package src.main.java.duke.command;

import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
    };

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }
}
