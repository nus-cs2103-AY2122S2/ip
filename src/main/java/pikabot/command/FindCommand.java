package pikabot.command;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.FindException;

/**
 * Represents a command to find a task based on a given search keyword.
 */
public class FindCommand extends Command {

    private String[] findCommand;

    /**
     * Constructs a findCommand.
     *
     * @param findCommand String array containing input from user.
     */
    public FindCommand(String[] findCommand) {
        this.findCommand = findCommand;
    }

    /**
     * Executes FindCommand by printing out a list of tasks that contain the search keyword.
     * @param taskList TaskList containing current tasks.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            Parser.parseFindCommand(findCommand);
            String keyword = findCommand[1];
            TaskList taskListWithMatchedTasks = new TaskList(taskList.find(keyword));
            System.out.println(Ui.printListOfMatchedTasks(taskListWithMatchedTasks, keyword));

        } catch (FindException e) {
            System.out.println(Ui.printExceptionMessage(e));
        }
    }
}
