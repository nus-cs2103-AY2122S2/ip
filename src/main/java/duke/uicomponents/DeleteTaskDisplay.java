package duke.uicomponents;

import duke.tasklist.TaskList;

/**
 * Displays to the user when a task is deleted.
 */
public class DeleteTaskDisplay {
    /**
     * Runs the screen when a task is deleted.
     *
     * @param task The task that was recently deleted.
     * @param taskList The task list which shows the number of tasks left.
     */
    public void run(String task, TaskList taskList) {
        System.out.println("============================");
        System.out.println("Deleted this tasks: ");
        System.out.println(task);
        taskList.printNoTasks();
        System.out.println("============================");
    }
}
