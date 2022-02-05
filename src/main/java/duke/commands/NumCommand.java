package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidOperationException;
import duke.tasks.TaskManager;
import duke.ui.UiManager;

/**
 * NumCommand Object that issues commands dealing with
 * number type input.
 */
public class NumCommand extends Command {
    private Integer num;
    private TaskManager taskManager;
    private UiManager uiManager;
    private Type type;

    /**
     * Constructs the NumCommand Object.
     *
     * @param um UiManager to handle input and output processes
     * @param tm TaskManager to handle process related to Task Objects
     * @param task String containing index of Task Object
     * @param t Type of the Task Object
     */
    public NumCommand(UiManager um, TaskManager tm, String task, Type t) {
        this.taskManager = tm;
        this.uiManager = um;
        this.type = t;
        this.num = Integer.parseInt(task) - 1;
    }

    /**
     * Apply changes to the Task object using the TaskManager Object.
     *
     * @return String containing the relevant Task Object
     * @throws IndexOutOfBoundsException if the index provided is invalid
     * @throws InvalidOperationException if the operation is invalid
     */
    public String execute() throws IndexOutOfBoundsException, InvalidOperationException, DukeException {
        switch (type) {
        case MARK:
            return taskManager.mark(num);
        case UNMARK:
            return taskManager.unmark(num);
        case DELETE:
            return taskManager.delete(num);
        default:
            throw new DukeException();
        }
    }
}
