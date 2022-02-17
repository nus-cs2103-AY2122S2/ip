package duke.commands;

import java.io.IOException;

import duke.exceptions.TaskIndexException;
import duke.tasks.TaskManager;
import duke.ui.UiManager;

/**
 * UpdateCommand object that issues an update command to the
 * relevant Task Object.
 */
public class UpdateCommand extends Command {
    private Integer num;
    private TaskManager taskManager;
    private UiManager uiManager;
    private String description;
    private Type type;

    /**
     * Constructs the UpdateCommand Object
     *
     * @param um UiManager to handle input and output processes
     * @param tm TaskManager to handle process related to Task Objects
     * @param task String containing index and description of Task Object
     * @param t Type of the Task Object
     * @throws TaskIndexException if an invalid 'task' String is given
     */
    public UpdateCommand(UiManager um, TaskManager tm, String task, Type t) throws TaskIndexException {
        this.taskManager = tm;
        this.uiManager = um;
        this.type = t;
        task = task.stripLeading();
        task = task.stripTrailing();
        String[] taskElements = task.split("\\s+", 2);
        if (taskElements.length == 1) {
            throw new TaskIndexException("'update'");
        }
        this.num = Integer.parseInt(taskElements[0]) - 1;
        this.description = taskElements[1] + " ";
        assert num >= 0 : "Invalid index";
    }

    /**
     * Apply an update to the Task's description using the TaskManager Object.
     *
     * @return String containing relevant Task object
     * @throws IOException if task is not serializable
     */
    @Override
    public String execute() throws IOException {
        String updateLabel = taskManager.update(num, description);
        this.taskManager.saveList();
        return updateLabel;
    }
}
