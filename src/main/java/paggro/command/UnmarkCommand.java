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
        int i;
        try {
            i = Integer.parseInt(this.parameters);
        } catch (NumberFormatException e) { // parameter was not a number
            throw new PaggroException("    Really? Can you input an actual number this time... =.=");
        }
        if (i > lister.tasks.size()) {
            throw  new PaggroException("    Really? There is no item indexed at " + i + "... =.=");
        }
        lister.unmark(i);
        Task task = lister.tasks.get(i - 1);
        ui.showUnmarked(task);

        try {
            storage.unmarkInStorage(i, task);
        } catch (IOException e) {
            throw new PaggroException("    Could not unmark in paggro.txt =.=");
        }
    }
}

