package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private final String text;

    /**
     * Constructs a {@code FindCommand} object.
     * @param text the string to search for
     */
    public FindCommand(String text) {
        this.text = text;
    }

    /**
     * Constructs a new {@code TaskList} with matching tasks and asks the UI to display the list.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Integer> matchingIndexes = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(text)) {
                matchingIndexes.add(i);
            }
        }
        if (matchingIndexes.size() == 0) {
            ui.showMessage("No matching tasks are found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i : matchingIndexes) {
                ui.showListItem(tasks, i);
            }
        }
    }

}
