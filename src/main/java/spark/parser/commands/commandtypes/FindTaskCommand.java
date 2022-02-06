package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.Ui;
import spark.commandresponse.CommandResponse;
import spark.commandresponse.SuccessResponse;
import spark.commandresponse.WarningResponse;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.tasks.tasktypes.Task;

/**
 * Represents a command for Spark to find a task.
 */
public class FindTaskCommand extends Command {
    private String searchTerm;
    private String responseMessage;

    /**
     * Creates a new command with the search-term that Spark
     * should use to search for Tasks with titles containing
     * the search-term.
     *
     * @param searchTerm search-term that Spark should like for in Task titles.
     */
    public FindTaskCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public List<CommandResponse> execute(TaskList tasks, Ui ui, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();
        responses.add(findAllMatchingTasks(tasks, searchTerm));
        return responses;
    }

    private CommandResponse findAllMatchingTasks(TaskList tasks, String searchTerm) {
        List<Task> matches = tasks.findTask(searchTerm);

        StringBuilder results = new StringBuilder();
        String noMatchingTaskMessage = "I couldn't find anything that matches what you are looking for.";

        if (matches.isEmpty()) {
            return new WarningResponse(noMatchingTaskMessage);
        } else {
            results.append("Okay, I've found these tasks: ");
            results.append(System.lineSeparator());

            for (Task t : matches) {
                results.append("    ");
                results.append(t.toString());
                results.append(System.lineSeparator());
            }
        }

        return new SuccessResponse(results.toString());
    }
}
