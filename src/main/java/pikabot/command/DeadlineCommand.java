package pikabot.command;


import java.io.IOException;
import java.time.format.DateTimeParseException;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.DeadlineException;
import pikabot.task.Deadline;

/**
 * Represents a command to create a Deadline task.
 */
public class DeadlineCommand extends Command {

    private String[] deadlineCommand;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param deadlineCommand String array containing input string from user.
     */
    public DeadlineCommand(String[] deadlineCommand) {
        this.deadlineCommand = deadlineCommand;
    }

    /**
     * Executes DeadlineCommand by creating a Deadline task.
     *
     * @param taskList TaskList to add the Deadline task to.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            Deadline currDeadline = Parser.parseDeadline(deadlineCommand);
            taskList.add(currDeadline);
            Ui.indicateAddedTask(currDeadline, taskList);
            storage.appendToFile(currDeadline);

        } catch (DeadlineException | IOException e) {
            Ui.printExceptionMessage(e);

        } catch (DateTimeParseException e) {
            Ui.printExceptionCustomisedMessage("Invalid deadline! Deadline has to "
                    + "be a valid date in numerical format YYYY-MM-DD.");
        }
    }
}
