package duke.command;

import java.util.ArrayList;

import duke.main.Storage;
import duke.main.TaskList;
import duke.task.Task;
import duke.ui.ImageType;
import duke.ui.Ui;


/**
 * Represents a Command which, when executed, finds all matching Task objects in the TaskList instance.
 */
public class FindCommand extends Command {
    private static final String BEGINNING_MESSAGE = "Charizard found some stuff from the burning list:";
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
        assert ui.hasEmptyMessage() : "Ui has leftover message from previous tasks";
        ArrayList<Task> matchedTasks = tasks.findTasks(searchDescription);
        ui.appendMessage(BEGINNING_MESSAGE);
        ui.appendBorder();
        String message = "";
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
