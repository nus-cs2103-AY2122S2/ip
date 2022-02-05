package duke.ui;
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
 * Custom DukeException to be handled by InputHandler
 */
public class DukeException extends Exception{
    private String errorMessage;
    public DukeException (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @override Returns customised error message for DukeException when input is incorrect
     * @return String errorMessage
     */
    public String getMessage() {return this.errorMessage;}
}
