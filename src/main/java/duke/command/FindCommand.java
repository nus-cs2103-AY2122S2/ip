package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

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
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws IOException {
        String keyword = this.textInput.substring(4).trim();
        if (keyword.trim().length() == 0) {
            System.out.println("Please enter a valid keyword!");
            ui.showLine();
            return "Please enter a valid keyword!";
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
            return "Oops! There were no tasks that matches your search!";
        }
        System.out.println("Here are the matching tasks in your list!");
        TaskList resTaskList = new TaskList(resArr);
        ui.displayList(count, resTaskList);
        return ui.showMatchingTasks(count, resTaskList);
    }

    /**
     * Signifies to the app to not terminate its current run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
