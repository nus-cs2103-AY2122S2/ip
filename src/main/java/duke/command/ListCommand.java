package duke.command;

import duke.task.Task;
import duke.util.Save;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This ListCommand class will show a list of added tasks when executed.
 */
public class ListCommand extends Command {

    /**
     * Executes command by showing a list of tasks in the current TaskList.
     *
     * @param tasks TaskList of tasks.
     * @param ui    Ui provided.
     * @param save  Saved history.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Save save) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int taskCount = 0; taskCount < tasks.getCount(); taskCount++) {
            Task mainTask = tasks.getTask(taskCount);
            System.out.println("\t " + (taskCount + 1) + "." + mainTask.track()
                    + mainTask.getStatus() + " " + mainTask);
        }
        System.out.println("\t____________________________________________________________");
    }
}
