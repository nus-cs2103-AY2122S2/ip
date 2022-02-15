package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents add new to do command.
 * Inherits from Command.
 */
public class AddTodoCommand extends Command {
    private final ToDo newTodo;

    /**
     * Returns a to-do command with new to do.
     *
     * @param newTodo new to do.
     */
    public AddTodoCommand(ToDo newTodo) {
        this.newTodo = newTodo;
    }

    /**
     * Returns a deadline command with new deadline.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks = tasks.add(newTodo);
        storage.saveTaskList(tasks);
        int taskIndex = tasks.getSize() - 1;
        ui.showMessage("Got it. I've added this task: \n        "
                + tasks.getByIndex(taskIndex) + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
