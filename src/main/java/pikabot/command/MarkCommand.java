package pikabot.command;

import java.io.IOException;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.NoIntegerException;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {

    private String[] markCommand;

    /**
     * Constructs a MarkCommand.
     *
     * @param markCommand String array containing input string from user.
     */
    public MarkCommand(String[] markCommand) {
        this.markCommand = markCommand;
    }

    /**
     * Executes MarkCommand by marking a task as done.
     *
     * @param taskList TaskList containing the task to be marked.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Parser.parseIntegerCommand(markCommand);
            int taskToMark = Integer.parseInt(markCommand[1]);
            taskList.markTaskAsDone(taskToMark);
            storage.taskListToFile(taskList);
            return Ui.indicateMarked(taskList.get(taskToMark - 1));
        } catch (NoIntegerException | IOException e) {
            return Ui.printExceptionMessage(e);
        } catch (IndexOutOfBoundsException e) {
            return Ui.printExceptionCustomisedMessage("☹ OOPS!!! "
                    + "The task number you entered does not exist.");
        }
    }
}
