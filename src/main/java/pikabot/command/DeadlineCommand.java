package pikabot.command;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;

import pikabot.task.Deadline;
import pikabot.exception.DeadlineException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    String[] deadlineCommand;

    public DeadlineCommand(String[] deadlineCommand) {
        this.deadlineCommand = deadlineCommand;
    }

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
            Ui.printExceptionCustomisedMessage("Invalid deadline! Deadline has to " +
                "be a valid date in numerical format YYYY-MM-DD.");
        }
    }
}
