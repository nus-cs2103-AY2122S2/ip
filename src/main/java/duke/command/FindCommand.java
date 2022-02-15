package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.ErrorString;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * FindCommand is a command that handles the find command.
 *
 * @author yl-ang
 */
public class FindCommand extends Command {

    private boolean isExit = false;
    private String keyword;

    /**
     * Constructs FindCommand object with the supplied user input.
     *
     * @param data Full user input.
     */
    public FindCommand(String data) {
        try {
            String[] temp = data.split(" ");
            this.keyword = temp[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorString.ERROR_EMPTY_FIND_KEYWORD.toString());
        }
    }

    /**
     * Executes FindCommand with the objects supplied.
     *
     * @param taskLst TaskLst object containing the current tasks.
     * @param ui Ui object containing the user interface messages and scanner object.
     * @param storage Storage object containing the methods to load and save.
     * @return Formatted response from executing command.
     */
    @Override
    public String execute(TaskList taskLst, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskLst.findTask(keyword);
        String response = ui.findResponse(tasks);
        assert response != null : "Find response should not be null";
        return response;
    }

    /**
     * Checks and returns the boolean whether if command is exit.
     *
     * @return Boolean if command is exit.
     */
    @Override
    public boolean isExit() {
        return isExit;
    }

}
