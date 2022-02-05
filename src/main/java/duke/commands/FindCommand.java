package duke.commands;

import duke.tasks.TaskManager;
import duke.ui.UiManager;

/**
 * FindCommand Object that searches for Task Objects in task list
 * using a keyword.
 */
public class FindCommand extends Command {
    private String taskName;
    private TaskManager taskManager;
    private UiManager uiManager;

    /**
     * Constructs FindCommand Object.
     *
     * @param um UiManager Object to handle input and output processes
     * @param tm TaskManager Object to receive Task Objects
     * @param task String representation of the keyword
     */
    public FindCommand(UiManager um, TaskManager tm, String task) {
        this.uiManager = um;
        this.taskManager = tm;
        this.taskName = task;
    }

    /**
     * Finds Task Objects associated with the keyword and prints them
     * using the UiManager Object.
     *
     * @return String containing the relevant Task Object
     */
    @Override
    public String execute() {
        String tasks = this.taskManager.findTasks(this.taskName);
        return uiManager.printFind(tasks, this.taskName);
    }
}
