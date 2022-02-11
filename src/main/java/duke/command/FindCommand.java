package duke.command;

import duke.task.Task;
import duke.task.TaskList;

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
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<Integer> matchingIndexes = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(text)) {
                matchingIndexes.add(i);
            }
        }
        if (matchingIndexes.size() == 0) {
            return "No matching tasks are found.";
        }
        String response = "Here are the matching tasks in your list:";
        for (int i : matchingIndexes) {
            response += "\n  " + i + ". " + tasks.get(i);
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
