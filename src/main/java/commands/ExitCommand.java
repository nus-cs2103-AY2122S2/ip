package commands;

import ui.Ui;

import tasklist.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    public ExitCommand() {}

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
