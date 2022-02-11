package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.SortBy;
import mickey.task.TaskList;

public class SortCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public SortCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Execute command.
     *
     * @param tasks List of tasks.
     * @param ui Ui to print feedback.
     * @param storage Storage to store tasks.
     * @return Response after executing command.
     * @throws MickeyException Exception for invalid commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        switch (getDescription()) {
        case "alphabetical":
            tasks.sortTasks(SortBy.ALPHABETICAL);
            break;
        case "deadline":
            tasks.sortTasks(SortBy.DEADLINE);
            break;
        default:
            throw new MickeyException("Failed to execute command");
        }
        return tasks.toString();

    }
}
