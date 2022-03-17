package pyke.command;

import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

public class ListCommand extends Command {
    /**
     * Constructs the default ListCommand.
     *
     * @param taskList the class store the task info.
     * @param ui the interface for output information.
     * @param storage in charge of file IO.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.outputText("Currently the list is empty. There is nothing to output.");
        } else {
            String outputText = "";
            for (int i = 1; i <= taskList.getSize(); i++) {
                outputText = outputText.concat(i + "." + taskList.getTaskOutputStyle(i - 1));
                if (i != taskList.getSize()) {
                    outputText = outputText.concat("\n");
                }
            }
            assert outputText != null;
            ui.outputText(outputText);
        }
    }

    @Override
    public String executeUi(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            return ui.outputUiText("Currently the list is empty. There is nothing to output.");
        } else {
            String outputText = "";
            for (int i = 1; i <= taskList.getSize(); i++) {
                outputText = outputText.concat(i + "." + taskList.getTaskOutputStyle(i - 1));
                if (i != taskList.getSize()) {
                    outputText = outputText.concat("\n");
                }
            }
            assert outputText != null;
            return ui.outputUiText(outputText);
        }
    }

    /**
     * Knows if this command will exit the program.
     *
     * @return true if this method will exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
