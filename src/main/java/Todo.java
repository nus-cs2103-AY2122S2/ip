package duke.task;
import duke.duke.Duke;
import duke.ui.Parser;
import duke.ui.DukeException;
import duke.ui.InputHandler;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
/**
 * Represents a Todo which is a subclass of Task
 * Includes a dueDate attribute. Overrides toString() from Task
 */

public class Todo extends Task {

    /**
     * Constructor for Todo
     * @param name Name of Todo
     */
    public Todo (String name) {
        super(name);
    }

    /**
     *
     * @return String of Todo task, eg: [T][X] Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}