package pikabot.command;

import java.io.IOException;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.NoIntegerException;
import pikabot.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private String[] deleteCommand;

    /**
     * Constructs a DeleteCommand.
     *
     * @param deleteCommand String array containing input string from user.
     */
    public DeleteCommand(String[] deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    /**
     * Executes DeleteCommand by deleting a task from TaskList.
     *
     * @param taskList TaskList containing task to be deleted.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Parser.parseIntegerCommand(deleteCommand);
            int taskNumberToDelete = Integer.parseInt(deleteCommand[1]);
            Task taskToDelete = taskList.get(taskNumberToDelete - 1);
            taskList.delete(taskNumberToDelete);
            storage.taskListToFile(taskList);
            return Ui.indicateRemovedTask(taskToDelete, taskList);
        } catch (NoIntegerException | IOException e) {
            return Ui.printExceptionMessage(e);
        } catch (IndexOutOfBoundsException e) {
            return Ui.printExceptionCustomisedMessage("☹ OOPS!!! "
                    + "The task number you entered does not exist.");
        }
    }
}
