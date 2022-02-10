package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.tasks.Task;
import duke.tasks.TaskToDo;

/**
 * The CommandToDo class contains basic attributes and
 * behaviours of a ToDo Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandToDo extends Command {
    /**
     * Name of Task to be added
     */
    private final String name;

    /**
     * Sole constructor.
     *
     * @param name - name of ToDo Task to be created
     */
    public CommandToDo(String name) {
        this.name = name;
    }

    /**
     * Overrides method in parent class.
     * This method models the execution of an ToDo Command.
     * A ToDo Task gets created, the TaskList adds the task,
     * the Storage saves it and the Ui lets the user know
     * that the task was executed successfully.
     *
     * @param tasks - for TaskList to add ToDo Task
     * @param ui - to let user know that execution was successful
     * @param storage - to save updated TaskList
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = new TaskToDo(this.name);
            tasks.add(task);
            storage.save(tasks);
            return ui.showTaskAdded();
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}
