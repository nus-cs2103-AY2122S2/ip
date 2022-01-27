package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Ui;

import java.io.IOException;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {

    String[] markCommand;

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
    public void execute(TaskList taskList, Storage storage) {
        int taskToMark = Integer.parseInt(markCommand[1]);
        taskList.markTaskAsDone(taskToMark);

        try {
            storage.TaskListToFile(taskList);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }

        Ui.indicateMarked(taskList.get(taskToMark - 1));
    }
}
