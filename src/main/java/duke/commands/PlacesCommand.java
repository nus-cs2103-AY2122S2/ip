package duke.commands;

import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command for finding places using keywords.
 */
public class PlacesCommand extends Command<String> {
    private TaskList list;
    private String[] echo;

    /**
     * Constructor for find class to execute the finding of places with specific keywords.
     *
     * @param list list to search from
     * @param echo the details of find search
     * @throws DukeException when find command cannot be executed
     */
    public PlacesCommand(TaskList list, String[] echo) throws DukeException {
        this.list = list;
        this.echo = echo;
    }

    /**
     * Executes the finding of place.
     *
     * @return a output response for finding place
     * @throws DukeException thrown when no findings
     */
    public String execute() throws DukeException {
        String response = "";
        String location = "";
        if (echo.length > 1) {
            location = echo[1];
        }
        int n = 1;
        String finalLocation = location;
        List<Task> filteredList = list.taskListToList().stream().filter(x -> (x.toString().contains("E")
                && x.isPlaceValid() && x.toString().contains(finalLocation)))
                .collect(Collectors.toList());
        response = "Here are the locations in your list:\n";
        for (int i = 0; i < filteredList.size(); i++) {
            String taskDetail = filteredList.get(i).toString();
            response = response + n + "." + taskDetail + "\n";
            n = n + 1;

        }
        return response;
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
