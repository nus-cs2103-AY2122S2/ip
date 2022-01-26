package duke.command;

import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a handler for the exit command.
 */
public class ExitCommand extends Command {
    /**
     * Creates a handler for the exit command.
     * @param args Arguments supplied to the command handler.
     */
    ExitCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) {
        linePrinter.print("Goodbye! Have a Nice Day.");
        return false;
    }
}
