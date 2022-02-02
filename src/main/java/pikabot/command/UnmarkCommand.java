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
    public void execute(TaskList taskList, Storage storage) {
        try {
            Parser.parseIntegerCommand(unmarkCommand);
            int taskToMark = Integer.parseInt(unmarkCommand[1]);
            taskList.markTaskAsUndone(taskToMark);
            System.out.println(Ui.indicateUnmarked(taskList.get(taskToMark - 1)));
        } catch (NoIntegerException e) {
            System.out.println(Ui.printExceptionMessage(e));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.printExceptionCustomisedMessage("☹ OOPS!!! "
                    + "The task number you entered does not exist."));
        }

        try {
            storage.taskListToFile(taskList);
        } catch (IOException e) {
            System.out.println(Ui.printExceptionMessage(e));
        }
    }

}
