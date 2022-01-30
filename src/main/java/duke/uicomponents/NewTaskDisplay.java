package duke.uicomponents;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Displays when the user adds a new task.
 */
public class NewTaskDisplay{
    /**
     * Runs the new task display.
     *
     * @param task The task that was recently added.
     * @param taskList The task list which will then print out the number of tasks now.
     */
    public void run(Task task, TaskList taskList) {
        System.out.println("====================================");
        System.out.println("Alright, I've added this to the list");
        System.out.println(task.toString());
        taskList.printNoTasks();
        System.out.println("====================================");
    }
}
