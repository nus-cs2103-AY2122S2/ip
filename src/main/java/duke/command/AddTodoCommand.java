package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;


public class AddTodoCommand extends Command {
    private final ToDo newTodo;

    public AddTodoCommand(ToDo newTodo) {
        this.newTodo = newTodo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks = tasks.add(newTodo);
        storage.saveTaskList(tasks);
        ui.showMessage("Got it. I've added this duke.task: \n        "
                + tasks.getByIndex(tasks.getSize() - 1) + "\n    Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
