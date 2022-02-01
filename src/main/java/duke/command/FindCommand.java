package duke.command;

import duke.TaskList;

public class FindCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "find";
    private final String searchQuery;

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
