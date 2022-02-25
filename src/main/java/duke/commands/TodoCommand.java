package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(tasks.size() + 1, this.description);
        tasks.add(todo);
        ui.showMessage("Got it. I've added the to-do task:");
        ui.showMessage(todo.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
