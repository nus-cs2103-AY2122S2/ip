package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.TaskArrayList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        ui.print("Buai Buai! Ciao for now!");
    }
}
