package pikabot.command;
import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Ui;
import pikabot.Parser;
import pikabot.task.Task;
import pikabot.exception.NoIntegerException;


import java.io.IOException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    String[] deleteCommand;

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
    public void execute(TaskList taskList, Storage storage) {
        try {
            Parser.parseIntegerCommand(deleteCommand);
            int taskNumberToDelete = Integer.parseInt(deleteCommand[1]);
            Task taskToDelete = taskList.get(taskNumberToDelete - 1);
            taskList.delete(taskNumberToDelete);
            Ui.indicateRemovedTask(taskToDelete, taskList);
        } catch (NoIntegerException e) {
            Ui.printExceptionMessage(e);
        } catch (IndexOutOfBoundsException e) {
            Ui.printExceptionCustomisedMessage("☹ OOPS!!! " + "The task number you entered does not exist.");
        }

        try {
            storage.TaskListToFile(taskList);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }
    }
}
