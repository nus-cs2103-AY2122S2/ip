package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.tasks.TaskList;
import duke.managers.Ui;

public class StoreTodoCommand extends StoreCommand {
    public StoreTodoCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        io.showMessage("Got it. I've added this task:\n       "
                + taskList.addTodoTask(tokens).toString()
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        super.execute(taskList, io, storage);
    }
}
