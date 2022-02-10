package athena.commands;

import java.util.ArrayList;
import java.util.List;

import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a find command given to Athena by the user. When executed, displays
 * the tasks containing the given search phrase.
 */
public class FindCommand extends Command {
    private final String searchPhrase;

    /**
     * Constructs a new FindCommand instance using the given search phrase.
     *
     * @param searchPhrase Search phrase to use when looking for matching tasks.
     */
    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    /**
     * Searches for tasks in the given task list containing the search phrase,
     * and displays them through the given ui. If no matching tasks are found, apology
     * text is displayed instead.
     *
     * @param taskList TaskList instance to search for matching tasks in.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        List<Integer> taskNumbers = taskList.getTaskNumbersContainingPhrase(searchPhrase);
        if (taskNumbers.size() > 0) {
            ArrayList<String> outputs = new ArrayList<>();
            outputs.add(String.format("Here are the tasks containing the phrase '%s'", searchPhrase));
            outputs.add(Messages.getSpecificTasksDialog(taskList, taskNumbers));
            return Messages.getMultiLineString(outputs);
        } else {
            return String.format("Sorry, no tasks were found containing the phrase '%s'", searchPhrase);
        }
    }
}
