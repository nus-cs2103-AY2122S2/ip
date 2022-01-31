package paggro.command;

import java.util.ArrayList;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates a find command which prints a list of tasks containing a given search key.
 */
public class FindCommand extends Command {
    /**
     * Constructor of FindCommand
     * @param parameters String key to be searched for.
     */
    public FindCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of the find command which prints a list of tasks containing a given search key.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        ArrayList<Task> tasks = lister.findIndicesContaining(this.getParameters());
        return ui.showList(tasks);
    }
}
