package duke.command;

import duke.TaskList;

public class FindCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "find";
    private final String searchQuery;

    /**
     * Creates a FindCommand object.
     *
     * @param taskList Task list where the search query will be matched with tasks from this list.
     * @param searchQuery Search query that will be matched.
     */
    public FindCommand(TaskList taskList, String searchQuery) {
        super(taskList);
        this.searchQuery = searchQuery;
    }

    @Override
    public CommandResult runCommand() {
        String results = taskList.search(searchQuery);
        if (results.isEmpty()) { // No results found
            return new CommandResult("No results were found");
        } else {
            return new CommandResult("Here are the matching tasks in your list:\n" + results);
        }
    }
}
