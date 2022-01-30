package paggro.command;

import java.io.IOException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates a mark command which marks a given task as done.
 */
public class MarkCommand extends Command {
    /**
     * Constructor of MarkCommand
     * @param parameters String of index to be marked as done.
     */
    public MarkCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of the mark command which marks the given task as done.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @throws PaggroException
     */
    @Override
    public void execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        int index;
        try {
            index = Integer.parseInt(this.getParameters());
        } catch (NumberFormatException e) { // parameter was not a number
            throw new PaggroException("    Really? Can you input an actual number this time... =.=");
        }
        if (index > lister.getTasks().size()) {
            throw new PaggroException("    Really? There is no item indexed at " + index + "... =.=");
        }
        lister.mark(index);
        Task task = lister.getTasks().get(index - 1);
        ui.showMarked(task);

        try {
            storage.markInStorage(index, task);
        } catch (IOException e) {
            throw new PaggroException("    Could not mark in paggro.txt =.=");
        }
    }
}
