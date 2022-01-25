package duke.commands;

import duke.managers.Storage;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.exceptions.DukeException;

public class StoreDeadlineCommand extends StoreCommand {
    public StoreDeadlineCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Got it. I've added this task:\n       "
                + taskList.addDeadlineTask(tokens).toString()
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        super.execute(taskList, io, storage);
    }
}
