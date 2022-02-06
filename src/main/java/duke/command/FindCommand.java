package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;


/**
 * Represents a command that will find any tasks stored in the
 * tasklist that contains a keyword provided.
 */
public class FindCommand extends Command {

    private String textInput;

    /**
     * Constructor for a FindCommand object.
     */
    public FindCommand(String input) {
        this.textInput = input;
    }

    /**
     * Will find any tasks that have a give keyword.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return The list of tasks that matches the keyword.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws IOException {
        String keyword = this.textInput.substring(4).trim();
        if (keyword.trim().length() == 0) {
            ui.showLine();
            return "Please enter a valid keyword!";
        }
        TaskList result = tasks.filterByWord(keyword);
        int count = result.getCount();
        if (count == 0) {
            return "Oops! There were no tasks that matches your search!";
        }
        return ui.showMatchingTasks(count, result);
    }

    /**
     * Signifies to the app to not terminate its current run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
