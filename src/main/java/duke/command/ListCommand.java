package duke.command;

import duke.exception.DukeException;
import duke.function.TaskList;
import duke.function.Storage;
import duke.function.Ui;
import duke.task.Task;


public class ListCommand extends Command {
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks(ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
