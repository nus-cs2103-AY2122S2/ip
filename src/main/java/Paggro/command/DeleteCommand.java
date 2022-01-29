package paggro.command;

import java.io.IOException;

import paggro.lister.Lister;
import paggro.ui.Ui;
import paggro.storage.Storage;
import paggro.exception.PaggroException;
import paggro.task.*;
import paggro.notableDate.NotableDate;


public class DeleteCommand extends Command{
    public DeleteCommand(String parameters) {
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
            throw  new PaggroException("    Really? There is no item indexed at " + i + "... =.=");
        }

        Task task = lister.tasks.get(index - 1);

        if (task instanceof Event) {
            Event e = (Event) task;
            NotableDate nDate = e.date;
            nDate.tasks.remove(task); // remove task from NotableDate tasklist
            if (nDate.tasks.isEmpty()) {
                lister.dateMap.remove(nDate.localDate); // if date is empty, remove from datemap
            }

        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            NotableDate nDate = d.date;
            nDate.tasks.remove(task); // remove task from NotableDate tasklist
            if (nDate.tasks.isEmpty()) {
                lister.dateMap.remove(nDate.localDate); // if date is empty, remove from datemap
            }
        }

        lister.delete(index);
        ui.showDeleted(task);
        ui.showNumber(lister.tasks.size());


        try {
            storage.deleteFromStorage(index);
        } catch (IOException e) {
            throw new PaggroException("    Could not delete in paggro.txt =.=");
        }
    }
}
