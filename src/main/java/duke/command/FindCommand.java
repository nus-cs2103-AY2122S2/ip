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

        final String searchTerm = this.args.toLowerCase();
        assert searchTerm.length() > 0;
        linePrinter.print("Here are the tasks related to your search:");
        taskList.doForEach((index, task) -> {
            assert task != null;
            if (task.getDescription().toLowerCase().contains(searchTerm)) {
                linePrinter.print(String.format("%d. %s", index + OFFSET_LOGICAL_TO_READABLE,
                        task.getReadableString()));
            }
        });
        return true;
    }
}
