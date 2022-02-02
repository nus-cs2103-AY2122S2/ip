package pikabot.command;

import java.io.IOException;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.NoIntegerException;

/**
 * Represents a command that unmarks a marked task.
 */
public class UnmarkCommand extends Command {

    private String[] unmarkCommand;

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
    public String execute(TaskList taskList, Storage storage) {
        try {
            Parser.parseIntegerCommand(unmarkCommand);
            int taskToMark = Integer.parseInt(unmarkCommand[1]);
            taskList.markTaskAsUndone(taskToMark);
            storage.taskListToFile(taskList);
            return Ui.indicateUnmarked(taskList.get(taskToMark - 1));
        } catch (NoIntegerException | IOException e) {
            return Ui.printExceptionMessage(e);
        } catch (IndexOutOfBoundsException e) {
            return Ui.printExceptionCustomisedMessage("☹ OOPS!!! "
                    + "The task number you entered does not exist.");
        }
    }

}
