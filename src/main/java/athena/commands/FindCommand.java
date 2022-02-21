package athena.commands;

import java.util.List;

import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a find command given to Athena by the user. When executed, finds
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
     * and returns the results. If no matching tasks are found, apology
     * text is returned instead.
     *
     * @param taskList TaskList instance to search for matching tasks in.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        List<Integer> taskNumbers = taskList.getTaskNumbersContainingPhrase(searchPhrase);
        if (taskNumbers.size() == 0) {
            return Messages.getNoMatchesFoundDialog(searchPhrase);
        }
        return Messages.getSpecificTasksFoundDialog(taskList, taskNumbers, searchPhrase);
    }
}
