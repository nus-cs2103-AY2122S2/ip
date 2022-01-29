package paggro.command;

import java.io.IOException;

import paggro.lister.Lister;
import paggro.ui.Ui;
import paggro.storage.Storage;
import paggro.exception.PaggroException;
import paggro.task.Task;

public class UnmarkCommand extends Command{
    public UnmarkCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        int index;
        try {
            index = Integer.parseInt(this.parameters);
        } catch (NumberFormatException e) { // parameter was not a number
            throw new PaggroException("    Really? Can you input an actual number this time... =.=");
        }
        if (index > lister.tasks.size()) {
            throw  new PaggroException("    Really? There is no item indexed at " + index + "... =.=");
        }
        lister.unmark(index);
        Task task = lister.tasks.get(index - 1);
        ui.showUnmarked(task);

        try {
            storage.unmarkInStorage(index, task);
        } catch (IOException e) {
            throw new PaggroException("    Could not unmark in paggro.txt =.=");
        }
    }
}

