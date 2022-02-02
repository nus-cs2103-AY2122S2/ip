package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed finds all tasks in the task list that matches a keyword.
 */
public class FindTaskCommand extends Command {
    private final String keyword;

    /**
     * Class constructor.
     *
     * @param keyword keyword used to match tasks.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in <code>taskList</code> that matches the designated keyword.
     * After that, prompts <code>ui</code> to display those tasks to user.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ArrayList<String> matches = new ArrayList<>();

        int numberOfTasks = taskList.getNumberOfTasks();
        for (int i = 0; i < numberOfTasks; i++) {
            String taskDescription = taskList.getDescriptionOfTaskAtIndex(i);
            if (taskDescription.contains(keyword)) {
                matches.add(taskDescription);
            }
        }

        if (matches.size() == 0) {
            ui.showMessage("There are no matching tasks in your list");
        } else {
            ui.showMessage("Here are the matching tasks in your list");
            for (String description : matches) {
                ui.showMessage(description);
            }
        }
    }
}
