package duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Finds for tasks based on keywords
 */
public class FindCommand extends Command {
    private String args;

    /**
     * Creates a FindCommand object.
     * 
     * @param args args is the keywords to use for finding tasks.
     */
    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String response = "";

        try {
            ArrayList<Task> allTasks = taskList.getTasks();
            StringBuilder sb = new StringBuilder();
            int length = allTasks.size();

            if (length == 0) {
                response = "No tasks found based on keyword! Also, quit lazing around!";
                return response;
            }

            sb.append("Here are the matching tasks in your list:\n");

            List<Task> filteredTasks =
                    allTasks
                    .stream()
                    .filter(task -> task.getDescription().contains(this.args))
                    .collect(Collectors.toList());
            int filteredLength = filteredTasks.size();

            if (filteredLength < 1) {
                response = "No tasks found based on keyword! Also, quit lazing around!";
                return response;
            }

            for (int i = 0; i < filteredLength; ++i) {
                sb.append(i + 1 + ". " + filteredTasks.get(i).toString());

                if (i + 1 != length) {
                    sb.append("\n");
                }
            }

            response = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
