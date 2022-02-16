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
    private String response = "Here are the locations in your list:\n";
    private String location = "";

    /**
     * Constructor for find class to execute the finding of places with specific keywords.
     *
     * @param list list to search from
     * @param echo the details of find search
     * @throws DukeException when find command cannot be executed
     */
    public PlacesCommand(TaskList list, String[] echo) throws DukeException {
        this.list = list;
        if (!echo[0].equalsIgnoreCase("places")) {
            throw new DukeException("Oh no! I fear I don't understand! Try again!\n");
        }
        this.echo = echo;
    }

    /**
     * Executes the finding of place.
     *
     * @return a output response for finding place
     * @throws DukeException thrown when no findings
     */
    public String execute() throws DukeException {
        if (echo.length > 1) {
            location = echo[1];
        }
        prepareResponse(location);
        return response;
    }
    private void prepareResponse(String location) {
        int n = 1;
        String finalLocation = location;
        List<Task> filteredList = list.taskListToList().stream().filter(x -> (x.toString().contains("E")
                && x.isPlaceValid() && x.toString().contains(finalLocation)))
                .collect(Collectors.toList());
        for (Task task : filteredList) {
            String taskDetail = task.toString();
            response += n + "." + taskDetail + "\n";
            n = n + 1;

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
