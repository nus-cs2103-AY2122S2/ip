package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.Task;
import aeromon.TaskArrayList;

/**
 * FindCommand class handles commands to search for tasks using the keywords.
 */
public class FindCommand extends Command {

    private static final String FOUND_MESSAGE = "N-n-n-n-no matching tasks found :( \n";
    private static final String NOT_FOUND_MESSAGE = "M-m-m-m-matching tasks found: \n";
    private static final String ENDING_MESSAGE = "Phew, that was a good search, remember to complete your tasks! \n";

    private String searchString;

    /**
     * Constructs the FindCommand object.
     * @param searchString the String to search for.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        TaskArrayList matchingTasks = new TaskArrayList();

        for (int i = 0; i < taskArrayList.getSize(); i++) {
            Task task = taskArrayList.get(i);

            if (task.getDescription().contains(searchString)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return NOT_FOUND_MESSAGE;
        } else {
            return FOUND_MESSAGE + matchingTasks.getTaskList() + ENDING_MESSAGE;
        }
    }
}
