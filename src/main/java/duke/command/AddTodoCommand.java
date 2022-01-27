package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.ToDo;

/**
 * Represents a Command which, when executed, adds a ToDo object into a given TaskList instance.
 */
public class AddTodoCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String description;

    /**
     * Creates a new AddTodoCommand instance with the initialized description and deadline.
     *
     * @param description Description of the ToDo object to be added.
     */
    public AddTodoCommand(String description) {
        super(IS_EXIT);
        this.description = description;
    }

    /**
     * Adds the ToDo object to the TaskList and displays the newly added Event on Ui.
     *
     * @param tasks The TaskList instance in which the ToDo object is added into.
     * @param ui The Ui object used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo newTodo = new ToDo(description);
        tasks.add(newTodo);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                Ui.ADD_MESSAGE, newTodo, tasks.getSize());
        ui.appendMessage(message);
    }
}
