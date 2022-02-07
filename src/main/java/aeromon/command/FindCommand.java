package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.Task;
import aeromon.task.TaskArrayList;

import java.util.ArrayList;

/**
 * FindCommand class handles commands to search for tasks using the keywords.
 */
public class FindCommand extends Command {

    private String searchString;

    /**
     * Constructs the FindCommand object.
     * @param searchString the String to search for.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        TaskArrayList matchingTasks = new TaskArrayList();

        for (int i = 0; i < taskArrayList.getSize(); i++) {
            Task task = taskArrayList.get(i);

            if (task.getDescription().contains(searchString)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.print("N-n-n-n-no matching tasks found :(");
        } else {
            ui.print("M-m-m-m-matching tasks found: ");
            ui.print(matchingTasks.getTaskList() + "Phew, that was a good search, remember to complete your tasks!");
        }
    }
}
