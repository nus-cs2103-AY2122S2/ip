package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Add a todo to the tasklist.
 */
public class AddTodoCommand extends Command {
    
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo to the task list. "
            + "Parameters: DESCRIPTION\n"
            + "Example: " + COMMAND_WORD
            + " todo read books";

    private final Task toAdd;

    public AddTodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toAdd);
        ui.showAddTask(toAdd, tasks.getSize());
    }
}
