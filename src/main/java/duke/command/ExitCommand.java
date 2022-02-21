package duke.command;

import duke.ContactList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Exit command. Exits Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructs exit command.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Exits Duke.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     * @param contacts List of contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        return ui.showExit();
    }

    /**
     * Checks if this is the exit command.
     *
     * @return True.
     */
    public boolean isExit() {
        return true;
    }

}
