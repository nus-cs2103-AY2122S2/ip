
package duke.exception;

/**
 * Class for handling exceptions in wrong index of the task list
 */
public class TaskOutOfBoundException extends DukeException {

    private int taskIndex;

    public TaskOutOfBoundException(String e, int taskIndex) {
        super(e);
        this.taskIndex = taskIndex;
    }

    @Override
    public String getMessage() {
        return  taskIndex + " is not inside the task list!";
    }
}
