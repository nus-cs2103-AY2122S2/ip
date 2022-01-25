package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Will find any tasks stored that contains a keyword provided.
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
     * Constructor for a FindCommand object.
     *
     * @param stg   Storage object that will write information to a file.
     * @param ui    Ui object that will handle I/O requests.
     * @param tasks TaskList object that contains all tasks.
     * @throws DukeException If an error related to the Duke app occurs.
     * @throws IOException   If an I/O error occurs.
     */
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        String keyword = this.textInput.substring(4).trim();
        if (keyword.trim().length() == 0) {
            System.out.println("Please enter a valid keyword!");
            ui.showLine();
            return;
        }
        int size = tasks.getCount();
        int count = 0;
        ArrayList<Task> resArr = new ArrayList<Task>();
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
            if (task.getName().contains(keyword)) {
                count++;
                resArr.add(task);
            }
        }
        if (count == 0) {
            System.out.println("Oops! There were no tasks that matches your search!");
            ui.showLine();
            return;
        }
        System.out.println("Here are the matching tasks in your list!");
        ui.displayList(count, new TaskList(resArr));
        ui.showLine();
    }

    /**
     * Signifies to not terminate the current run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
