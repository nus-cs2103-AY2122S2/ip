package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.TaskList;

/**
 * Bye command to exit app.
 */
public class ByeCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public ByeCommand(String fullCommand) {
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
        storage.save(tasks);
        return ui.showBye();
    }
}
