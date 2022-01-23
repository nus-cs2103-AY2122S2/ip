package helperClasses.command;

import helperClasses.Storage;
import helperClasses.TaskList;
import helperClasses.Ui;

public class ExitCommand extends Command{

    public ExitCommand(){

    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        ui.closeReading();
    }
}
