package duke.uicomponents;

import duke.tasklist.TaskList;

/**
 * The display that shows all the task in the task lists.
 */
public class TaskListDisplay{
    /**
     * Runs the task list display.
     *
     * @param taskList The task list which will show all its tasks.
     */
    public void run(TaskList taskList) {
        System.out.println("==============================");
        System.out.println("Here You go: ");
        taskList.printTasks();
        System.out.println("==============================");

    }
}
