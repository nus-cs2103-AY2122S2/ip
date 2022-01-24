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
        if (tasks.size() == 0) {
            System.out.println("You currently do not have any tasks *quack*, please add some more");
        } else {
            ui.print("These are your tasks *quack*:");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.getByNumber(i);
                ui.print(String.format("%d. %s", i, task.toString()));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
