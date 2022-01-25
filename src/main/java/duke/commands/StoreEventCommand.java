package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.managers.TaskList;
import duke.managers.Ui;

public class StoreEventCommand extends StoreCommand {
    public StoreEventCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Got it. I've added this task:\n       "
                + taskList.addEventTask(tokens).toString()
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        super.execute(taskList, io, storage);
    }
}
