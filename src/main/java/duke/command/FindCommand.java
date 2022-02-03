package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a handler for the find command.
 */
public class FindCommand extends Command {
    /**
     * Creates a handler for the find command.
     *
     * @param args Arguments supplied to the command handler.
     */
    FindCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        if (this.args.equals("")) {
            throw new DukeIllegalArgumentException("Search term cannot be empty");
        }

        linePrinter.print("Here are the tasks related to your search:");
        taskList.doForEach((index, task) -> {
            if (task.getDescription().toLowerCase().contains(this.args.toLowerCase())) {
                linePrinter.print(String.format("%d. %s", index + 1, task.getReadableString()));
            }
        });
        return true;
    }
}
