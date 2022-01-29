package paggro.command;

import java.io.IOException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.task.Task;
import paggro.task.ToDo;
import paggro.ui.Ui;

public class ToDoCommand extends Command{
    public ToDoCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        Task task = new ToDo(this.parameters);
        lister.add(task);

        try {
            storage.addToStorage(task);
        } catch (IOException e) {
            throw new PaggroException("    Could not add to paggro.txt =.=");
        }

        ui.showAdded(task);
        ui.showNumber(lister.tasks.size());
    }
}
