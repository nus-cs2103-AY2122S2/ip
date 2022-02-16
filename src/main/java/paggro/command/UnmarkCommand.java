package paggro.command;

import java.io.IOException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates a unmark command which unmarks a given task as not done.
 */
public class UnmarkCommand extends Command {
    private static final String FOUR_SPACE = "    ";

    /**
     * Constructor of UnmarkCommand
     *
     * @param parameters String of index to be unmarked as not done.
     */
    public UnmarkCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of the unmark command which unmarks the given task as not done.
     *
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        int index;
        try {
            index = Integer.parseInt(this.getParameters());
        } catch (NumberFormatException e) { // parameter was not a number
            final String invalidInputError = "Really? Can you input an actual number this time... =.=";
            throw new PaggroException(FOUR_SPACE + invalidInputError);
        }

        if (index > lister.getTasks().size()) {
            final String invalidIndexError = "Really? There is no item indexed at " + index + "... =.=";
            throw new PaggroException(FOUR_SPACE + invalidIndexError);
        }

        lister.unmark(index);
        Task task = lister.getTasks().get(index - 1);

        try {
            storage.updateInStorage(index, task);
        } catch (IOException e) {
            throw new PaggroException("    Could not unmark in paggro.txt =.=");
        }

        return ui.showUnmarked(task);
    }
}

