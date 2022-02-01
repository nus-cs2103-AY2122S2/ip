package command;

import duke.Storage;
import duke.Ui;
import duke.UiForGUI;
import task.TaskList;

/**
 * The ListCommand class is a type of Command that is used to list the tasks saved.
 */
public class ListCommand extends Command {
    public ListCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    /**
     * Executes the list command and displays the list of tasks saved.
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, UiForGUI ui, Storage storage) {
        ui.printMsg(tasks.toString());
    }
}
