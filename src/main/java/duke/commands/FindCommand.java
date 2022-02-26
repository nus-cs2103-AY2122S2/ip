package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand implements Command {

    private String keyword;

    /**
     * Instantiates a new Find command.
     *
     * @param keyword keyword to find
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        boolean foundMatch = false;
        int counter = 1;
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i).matches(this.keyword)) {
                if (!foundMatch) {
                    ui.showMessage("Here are the matching tasks in your list:");
                    foundMatch = true;
                }
                ui.showMessage(counter + ". " + tasks.get(i).toString());
                counter++;
            }
        }
        if (!foundMatch) {
            ui.showMessage("No tasks regarding " + this.keyword + " found.");
        }

    }
}
