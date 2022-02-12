package duke.commands;

import java.io.IOException;

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
        assert num >= 0 : "Invalid index";
    }

    /**
     * Applies changes to the Task object using the TaskManager Object.
     *
     * @return String containing the relevant Task Object
     * @throws IndexOutOfBoundsException if the index provided is invalid
     * @throws InvalidOperationException if the operation is invalid
     * @throws DukeException if the command is invalid
     * @throws IOException if the task is not serializable
     */
    public String execute() throws IndexOutOfBoundsException, InvalidOperationException, DukeException, IOException {
        switch (type) {
        case MARK:
            String markLabel = taskManager.mark(num);
            this.taskManager.saveList();
            return markLabel;
        case UNMARK:
            String unmarkLabel = taskManager.unmark(num);
            this.taskManager.saveList();
            return unmarkLabel;
        case DELETE:
            String deleteLabel = taskManager.delete(num);
            this.taskManager.saveList();
            return deleteLabel;
        default:
            throw new DukeException();
        }
    }
}
