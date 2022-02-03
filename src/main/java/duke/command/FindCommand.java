package duke.command;

import duke.Ui;
import duke.io.Storage;
import duke.task.TaskList;

/**
 * Represents a command to find matching input in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class FindCommand extends Command {

    private String searchInput;

    /**
     *  Constructor to create a Find Command.
     *
     * @param searchInput Input text to be search for task to be searched.
     */
    public FindCommand(String searchInput) {
        this.searchInput = searchInput;
    }

    /**
     * Execute the command to find the task(s).
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     */
    public void execute(TaskList taskList, Storage storage) {
        StringBuffer result = new StringBuffer("Here are the matching task(s) in your list:\n");
        int count = 1;
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            if (taskList.getTask(i).getDescription().toLowerCase().contains(this.searchInput)) {
                result.append(count).append(".").append(taskList.getTask(i).toString()).append("\n");
                count++;
            }
        }
        if (count == 1) {
            Ui.print(Ui.MSG_NOMATCH);
        } else {
            Ui.print(result.toString().trim());
        }
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return """
                To find a task, use the find command.
                  Usage: find <search term> | i.e. find homework\s

                """;
    }
}
