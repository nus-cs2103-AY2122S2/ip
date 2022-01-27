package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Ui;

import java.io.IOException;

/**
 * Represents a command that unmarks a marked task.
 */
public class UnmarkCommand extends Command {

    String[] unmarkCommand;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param markCommand String array containing input string from user.
     */
    public UnmarkCommand(String[] markCommand) {
        this.unmarkCommand = markCommand;
    }

    /**
     * Executes UnmarkCommand by unmarking a marked task.
     *
     * @param taskList TaskList containing the task to be unmarked.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {

        int taskToUnmark = Integer.parseInt(unmarkCommand[1]);
        taskList.markTaskAsUndone(taskToUnmark);

        try {
            storage.TaskListToFile(taskList);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }

        Ui.indicateUnmarked(taskList.get(taskToUnmark - 1));
    }
}
