package pyke.command;

import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

public class ExitCommand extends Command{
    /**
     * Execute a command that indicates the exit of the program
     * Then it will output certain farewell words
     * @param taskList the class store the tasks info
     * @param ui the interface for output information
     * @param storage in charge of file IO
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayFarewell();
    }

    /**
     * To know if this command will exit the program
     *
     * @return true if this method will exit the program
     */
    public boolean isExit() {
        return true;
    }
}
