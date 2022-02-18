package duke;

import java.io.IOException;

/**
 * Represents a find command
 */
public class FindCommand extends Command {

    private final String searchWord;

    /**
     * Constructs an instance of the FindCommand class.
     *
     * @param command A String containing the word "find".
     * @param searchWord A String representing the query.
     */
    public FindCommand(String command, String searchWord) {
        super(command);
        this.searchWord = searchWord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException {
        int taskCount = 0;
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(this.searchWord)) {
                taskCount += 1;
                output.append("\n").append(taskCount).append(".").append(task);
            }
        }
        if (taskCount == 0) {
            return "Uh-oh! There are no matching tasks in your list!";
        } else {
            return output.toString();
        }
    }
}
