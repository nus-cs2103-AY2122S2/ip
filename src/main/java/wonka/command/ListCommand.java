package wonka.command;

import wonka.task.Task;
import wonka.util.Save;
import wonka.util.TaskList;

/**
 * This ListCommand class will show a list of added tasks when executed.
 */
public class ListCommand extends Command {

    /**
     * Executes command by showing a list of tasks in the current TaskList.
     *
     * @param tasks TaskList of tasks.
     * @param save  Saved history.
     */
    @Override
    public String execute(TaskList tasks, Save save) {
        String response = "Here are the tasks in your list:\n";
        for (int taskCount = 0; taskCount < tasks.getCount(); taskCount++) {
            Task mainTask = tasks.getTask(taskCount);
            response = response.concat((taskCount + 1) + "." + mainTask.track()
                    + mainTask.getStatus() + " " + mainTask + "\n");
        }
        return response;
    }
}
