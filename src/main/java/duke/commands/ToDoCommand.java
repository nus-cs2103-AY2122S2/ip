package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

/**
 * The ToDoCommand class contains basic attributes and
 * behaviours of a todo command.
 *
 * @author  Melvin Chan Zijun
 */
public class ToDoCommand extends Command {
    /**
     * Name of task
     */
    private final String name;

    /**
     * Sole constructor.
     *
     * @param name name of task
     */
    public ToDoCommand(String name) {
        this.name = name;
    }

    /**
     * Overrides method in parent class.
     * Executes the todo command, saves the data and returns
     * a message to let user know that execution was
     * successful.
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     * @return String message of either successful or
     *                unsuccessful execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = new ToDoTask(this.name);
            tasks.add(task);
            storage.save(tasks);
            return ui.showTaskAdded();
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}
