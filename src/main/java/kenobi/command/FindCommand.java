package kenobi.command;

import kenobi.util.TaskList;

/**
 * The FindCommand class encapsulates the command to find a Task to a given TaskList.
 */
public class FindCommand extends Command {
    final String searchTerm;

    /**
     * Constructs a FindCommand with the given search term to find the desired Task.
     *
     * @param searchTerm The term to be searched in the TaskList.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the search of a Task in the TaskList.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        TaskList found = tasks.search(searchTerm);

        if (found.size() == 0) {
            return "Nothing in the archives matches your search term";
        }

        return "Here's what I found from the archives:\n" + found;
    }
}
