package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

/**
 * Represents a todo command given to Athena by the user. When executed, creates
 * a new todo task in the given task list.
 */
public class TodoCommand extends Command {
    private final String taskName;

    /**
     * Constructs a new TodoCommand instance with the given task name.
     *
     * @param taskName Name of the todo task to be created.
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Creates a new todo task in the given taskList and displays the outputs
     * on the given ui.
     *
     * @param ui Ui instance to display outputs through.
     * @param taskList TaskList instance to create the todo task on.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = taskList.addTodo(taskName);
        ui.sayTaskAddingLines(taskNumber);
    }

    /**
     * Returns true if given object is also a TodoCommand and name of
     * todo task to be created matches that of the given TodoCommand.
     *
     * @param other Object to compare current instance to.
     * @return True if 'other' is also a TodoCommand and will create a todo task with the same name.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof TodoCommand) {
            TodoCommand otherCommand = (TodoCommand) other;
            return this.taskName.equals(otherCommand.taskName);
        } else {
            return false;
        }
    }
}

