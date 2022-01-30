package duke.commands;

import duke.*;
import duke.exceptions.DukeException;
import duke.exceptions.FailedTaskParseException;
import duke.tasks.Task;

public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            Task task = Parser.parseToTask(this.userInput);
            taskManager.addTask(task);
            ui.showAddedTask(task, taskManager.size());
            save(storage,ui,taskManager);
            return true;
        } catch (FailedTaskParseException exception) {
            throw new DukeException("Wrong Format!");
        }
    }
}
