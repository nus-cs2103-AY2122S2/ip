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
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        String output = "";

        boolean foundMatch = false;
        int counter = 1;
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i).matches(this.keyword)) {
                if (!foundMatch) {
                    output += "Here is a list of tasks that match " + this.keyword + ":";
                    foundMatch = true;
                }
                output += counter + ". " + tasks.get(i);
                counter++;
            }
        }
        if (!foundMatch) {
            output = "No tasks regarding \"" + this.keyword + "\" found.";
        }

        return output;
    }
}
