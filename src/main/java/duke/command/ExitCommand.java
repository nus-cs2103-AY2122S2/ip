package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * A representation of the command for printing goodbye message and exit.
 */
public class ExitCommand extends Command {
    /**
     * Executes exit command.
     *
     * @param tasks   TaskList class
     * @param ui      Ui class
     * @param storage Storage class
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printGoodBye();
//        System.exit(0);
    }
}
