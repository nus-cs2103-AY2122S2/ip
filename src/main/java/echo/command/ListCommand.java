package echo.command;

import echo.main.EchoException;
import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;

/**
 * This class inherits from the Command class and encapsulates the list command.
 */
public class ListCommand extends Command {

    /** String that represents the list command. */
    public static final String COMMAND = "list";

    /**
     * Execute command.
     *
     * @param tasks TaskList containing list of tasks.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showEmptyList();
        } else {
            // Task list is not empty. Prints all tasks.
            StringBuilder listDescription = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String taskStatus = tasks.taskStatus(i) + ("\n");
                if (i != 0) {
                    taskStatus = Ui.addPrefix(taskStatus);
                }
                listDescription.append(taskStatus);
            }
            listDescription.setLength(listDescription.length() - 1);
            ui.showList(listDescription.toString());
        }
    }
}
