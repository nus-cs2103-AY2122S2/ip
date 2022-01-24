package duke.command.action;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.IPrintable;

public class ListCommand extends Command {
    public ListCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) {
        linePrinter.print("This is your task list:");
        taskList.forEach((index, task) -> {
            // Note that index passed into this consumer is 0-based. Increment by 1 for readability
            linePrinter.print(String.format("%d. %s", index + 1, task.getReadableString()));
        });
        return true;
    }
}
