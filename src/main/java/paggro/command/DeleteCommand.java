package paggro.command;

import java.io.IOException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.notabledate.NotableDate;
import paggro.storage.Storage;
import paggro.task.Deadline;
import paggro.task.Event;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates a delete command which removes a task entry from the list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor of DeleteCommand.
     *
     * @param parameters String of index to be deleted
     */
    public DeleteCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of a delete command which removes a task from the list.
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
            throw new PaggroException("    Really? Can you input an actual number this time... =.=");
        }
        if (index > lister.getTasks().size()) {
            throw new PaggroException("    Really? There is no item indexed at " + index + "... =.=");
        }

        Task task = lister.getTasks().get(index - 1);

        if (task instanceof Event) {
            Event e = (Event) task;
            NotableDate nDate = e.getDate();
            nDate.getTasks().remove(task); // remove task from NotableDate tasklist
            if (nDate.getTasks().isEmpty()) {
                lister.getDateMap().remove(nDate.getLocalDate()); // if date is empty, remove from datemap
            }

        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            NotableDate nDate = d.getDate();
            nDate.getTasks().remove(task); // remove task from NotableDate tasklist
            if (nDate.getTasks().isEmpty()) {
                lister.getDateMap().remove(nDate.getLocalDate()); // if date is empty, remove from datemap
            }
        }

        lister.delete(index);

        try {
            storage.deleteFromStorage(index);
        } catch (IOException e) {
            throw new PaggroException("    Could not delete in paggro.txt =.=");
        }

        return ui.showDeleted(task) + "\n" + ui.showNumber(lister.getTasks().size());
    }
}
