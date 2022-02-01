package command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.UiForGUI;
import task.Task;
import task.TaskList;

/**
 * The FindCommand class is a type of Command that is used to find the matching tasks.
 */
public class FindCommand extends Command {
    public FindCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    /**
     * Executes the find command and displays the list of tasks that matches the keyword.
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, UiForGUI ui, Storage storage) {
        ArrayList<Task> tasksToBeFound = tasks.findTasks(command.substring(5));
        ui.printMsg(tasks.displayTasks("Here are the matching tasks in your list: ", tasksToBeFound, true));
    }
}
