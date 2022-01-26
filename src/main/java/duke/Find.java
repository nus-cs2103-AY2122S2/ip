package duke;

/**
 * Find class to act as a searcher for keywords.
 */
public class Find {

    /**
     * Constructs Find object.
     */
    public Find() {
    }

    /**
     * Returns TaskList containing all tasks which have a description that contains the keyword provided.
     *
     * @param currentTasks TaskList containing all current tasks.
     * @param keyword   Keyword to search for.
     * @return  TaskList containing all tasks which have a description that contains the keyword provided.
     */
    public TaskList find(TaskList currentTasks, String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : currentTasks.getTaskList()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
