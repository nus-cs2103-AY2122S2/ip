package pikabot.command;

import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    private String[] listCommand;

    /**
     * Constructs a ListCommand.
     *
     * @param listCommand String array containing input string from user.
     */
    public ListCommand(String[] listCommand) {
        this.listCommand = listCommand;
    }

    /**
     * Executes ListCommand by printing out existing tasks in a list.
     *
     * @param taskList TaskList containing list of tasks to print.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printListOfTasks(taskList);
    }
}
