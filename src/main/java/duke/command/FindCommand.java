package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.IPrintable;

import java.time.LocalDateTime;

public class FindCommand extends Command {
    FindCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        if (this.args.equals("")) {
            throw new DukeIllegalArgumentException("Search term cannot be empty!");
        }

        linePrinter.print("Here are the tasks related to your search:");
        taskList.forEach((index, task) -> {
            if (task.getDescription().toLowerCase().contains(this.args)) {
                linePrinter.print(String.format("%d. %s", index + 1, task.getReadableString()));
            }
        });
        return true;
    }
}
