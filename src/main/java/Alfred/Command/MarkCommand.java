package Alfred.Command;

import Alfred.Exceptions.InvalidIndexException;
import Alfred.Exceptions.InvalidInputException;
import Alfred.Storage.AlfredStorage;
import Alfred.UI.AlfredUserInterface;

public class MarkCommand extends Command {
    private String[] arguments;

    public MarkCommand(String input) {
        this.arguments = input.split(" ");
    }

    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) throws InvalidInputException,
            InvalidIndexException {
        // check only two arguments
        if (arguments.length != 2) {
            throw new InvalidInputException();
        }
        // check that second argument is numerical
        int taskId;
        try {
            taskId = Integer.valueOf(arguments[1]) - 1;
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException();
        }
        // modify in storage
        storage.markTask(taskId);

        // print out
        String out = "Good job sir. I've marked this as complete.\n";
        out += storage.taskToString(taskId);
        ui.sandwichAndPrint(out);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
