package duke.command;

import java.util.ArrayList;

import duke.main.ImageType;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a Command which, when executed, finds all matching Task objects in the TaskList instance.
 */
public class FindCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String searchDescription;

    /**
     * Creates a new FindCommand instance with the specified search description.
     *
     * @param searchDescription The text which descriptions of matching tasks are expected to contain
     */
    public FindCommand(String searchDescription) {
        super(IS_EXIT);
        this.searchDescription = searchDescription;
    }

    /**
     * Displays all matching Task objects from the given TaskList instance.
     *
     * @param tasks The TaskList instance to be searched.
     * @param ui The Ui object used for displaying the Task objects.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchedTasks = tasks.findTasks(searchDescription);
        String message = "Charizard found some stuff from the burning list:";
        ui.appendMessage(message);
        ui.appendBorder();
        message = "";
        for (int i = 0; i < matchedTasks.size(); i++) {
            message += String.format("%d. %s", i + 1, matchedTasks.get(i));
            if (i < matchedTasks.size() - 1) {
                message += "\n";
            }
        }
        ui.appendMessage(message);
        ui.setRespondImage(ImageType.GENERAL);
    }
}
