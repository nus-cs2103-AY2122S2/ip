package pyke.command;

import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

public class ListCommand extends Command{
    /**
     * Execute a command that will print the list of tasks. It will specify if the list is empty
     *
     * @param taskList the class store the tasks info
     * @param ui the interface for output information
     * @param storage in charge of file IO
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.outputText("Currently the list is empty. There is nothing to output.");
        } else {
            String outputText = "";
            for (int i = 1; i <= taskList.getSize(); i++) {
                outputText = outputText.concat(i + "." + taskList.getTaskOutputStyle(i - 1));
                if (i != taskList.getSize()) outputText = outputText.concat("\n");
            }
            ui.outputText(outputText);
        }
    }

    /**
     * To know if this command will exit the program
     *
     * @return true if this method will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
