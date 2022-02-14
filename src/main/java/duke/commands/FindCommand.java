package duke.commands;

import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for finding tasks using keywords.
 */
public class FindCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private final String err = "Oh no! The description of find cannot be empty... Try again :)\n";
    private String response = "Here are the matching tasks in your list:\n";

    /**
     * Constructor for find class to execute the finding of tasks with specific keywords.
     *
     * @param list list to search from
     * @param echo the details of find search
     * @throws DukeException when find command cannot be executed
     */
    public FindCommand(TaskList list, String[] echo) throws DukeException {
        this.list = list;
        this.echo = echo;
    }

    /**
     * Executes the finding of task.
     *
     * @return a output response for finding tasks
     * @throws DukeException thrown when no findings
     */
    public String execute() throws DukeException {
        checkValidity(echo.length == 1);
        String keyword = echo[1];
        checkValidity(keyword.isEmpty());
        prepareResponse(keyword);
        return response;
    }
    private void prepareResponse(String keyword) {
        int n = 1;
        List<Task> filteredList = list.taskListToList().stream().filter(x -> x.toString().contains(keyword))
                .collect(Collectors.toList());
        for (Task task : filteredList) {
            String taskDetail = task.toString();
            response += n + "." + taskDetail + "\n";
            n = n + 1;

        }
    }
    private void checkValidity(boolean bool) throws DukeException {
        if (bool) {
            assert false : "The task to find is not specified";
            throw new DukeException(err);
        }
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop bot from running
     */
    public boolean isExit() {
        return false;
    }
}
