package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

public class ShutdownCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.sayText("Farewell!");
        // main loop needs to check that this is an exit command
    }
}
