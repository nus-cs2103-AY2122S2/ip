package duke.command;

import duke.TaskList;
import duke.util.IPrintable;

public class ExitCommand extends Command {
    public ExitCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) {
        linePrinter.print("Goodbye! Have a Nice Day.");
        return false;
    }
}
