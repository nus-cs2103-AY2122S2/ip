package duke.command;

import duke.task.TaskList;
import duke.util.IPrintable;

public class ExitCommand extends Command {
    ExitCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) {
        linePrinter.print("Goodbye! Have a Nice Day.");
        return false;
    }
}
