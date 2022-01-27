package pikabot.command;

import pikabot.Ui;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.exception.InvalidTaskCommandException;

public class InvalidCommand extends Command {

    String[] invalidCommand;

    public InvalidCommand(String[] invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    public static void invalidTask() throws InvalidTaskCommandException {
        throw new InvalidTaskCommandException();
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            invalidTask();
        } catch (InvalidTaskCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }
}
