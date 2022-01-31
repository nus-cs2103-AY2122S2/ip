package paggro.command;

import java.io.IOException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.task.Task;
import paggro.task.ToDo;
import paggro.ui.Ui;

/**
 * This class encapsulates an todo command which creates a new event entry.
 */
public class ToDoCommand extends Command {
    /**
     * Constructor of ToDoCommand object.
     * @param parameters String containing event description.
     */
    public ToDoCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of a ToDocommand which creates a new ToDo object.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        Task task = new ToDo(this.getParameters());
        lister.add(task);

        try {
            storage.addToStorage(task);
        } catch (IOException e) {
            throw new PaggroException("    Could not add to paggro.txt =.=");
        }

        return ui.showAdded(task) + "\n" + ui.showNumber(lister.getTasks().size());
    }
}
