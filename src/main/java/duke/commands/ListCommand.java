package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "ListCommand[execute] taskList cannot be null.";
        assert storage != null : "ListCommand[execute] storage cannot be null.";

        String response = "";
        int length = taskList.size();
        StringBuilder sb = new StringBuilder();

        if (length == 0) {
            response = "No tasks found! Quit lazing around!";
            return response;
        }

        ArrayList<Task> allTasks = taskList.getTasks();

        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < length; ++i) {
            sb.append(i + 1 + ". " + allTasks.get(i).toString());

            if (i + 1 != length) {
                sb.append("\n");
            }
        }

        response = sb.toString();

        return response;
    }
}
