package duke.commands;

import duke.data.TasksEditor;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Adds a to-do to the tasklist.
 */
public class AddTodoCommand extends Command {
    
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo to the task list.\n\t"
            + "Parameters: DESCRIPTION\n\t"
            + "Example: " + COMMAND_WORD + " read books";

    private final Task toAdd;

    public AddTodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        tasksEditor.add(toAdd);
        return ui.showAddTask(toAdd, tasksEditor.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddTodoCommand)) {
            return false;
        }

        AddTodoCommand command = (AddTodoCommand) obj;
        return this.toAdd.equals(command.toAdd);
    }
}
