package duke.uicomponents;

import duke.tasklist.TaskList;

/**
 * Displays all the tasks which contains the task list.
 */
public class FilteredTaskListDisplay {
    /**
     * Runs the Filter task List Display.
     *
     * @param keyword The keyword in the query.
     * @param filteredTaskList The task list that contains tasks with the task list.
     */
    public void run(String keyword, TaskList filteredTaskList) {
        System.out.println("==========================");
        System.out.println("Here are the tasks which contains \"" + keyword + "\":");
        filteredTaskList.printTasks();
        System.out.println("==========================");
    }
}
