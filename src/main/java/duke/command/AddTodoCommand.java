package duke.command;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.TextUi;

/**
 * A class that adds todo object into a task list.
 */
public class AddTodoCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;

    /**
     * Creates a AddTodoCommand instance with a title.
     *
     * @param title The title of the todo.
     */
    public AddTodoCommand(String title) {
        super(IS_EXIT);
        this.title = title;
    }

    /**
     * Adds the todo object to into the task list and show the execution message on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return The string of the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = new Todo(title);
            tasks.addTask(task);
            storage.saveAllTasks(tasks);
            return TextUi.showExecutionMessage(Messages.MESSAGE_ADD_TODO, task.toString(), tasks.getSize());
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
