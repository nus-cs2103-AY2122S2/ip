package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private final ToDo toDo;

    public ToDoCommand(String description) {
        toDo = new ToDo(description);
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(toDo);
        ui.showTaskAdded(toDo);
        ui.showNumberOfTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
