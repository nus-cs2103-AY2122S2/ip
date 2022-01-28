package pyke.command;

import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;


public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keywords) {
        this.keyword = keywords;
    }

    /**
     * This method will execute a find command. It will search in evey task description for the keywords
     * If that task contains the keyword, it will be printed out
     *
     * @param taskList the class store the tasks info
     * @param ui the interface for output information
     * @param storage in charge of file IO
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.outputText("Currently the list is empty. There is nothing to output.");
        } else {
            int cnt = 0;
            String outputText = "";
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.hasKeyword(i, keyword)) {
                    cnt++;
                    outputText = outputText.concat("\n" + cnt + "." + taskList.getTaskOutputStyle(i));
                }
            }
            if (cnt != 0) {
                ui.outputText("Here are the matching tasks in your list:" + outputText);
            } else {
                ui.outputText("Sorry! I couldn't find any task which contains that keyword.");
            }
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
