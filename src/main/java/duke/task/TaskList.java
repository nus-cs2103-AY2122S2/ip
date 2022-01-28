package duke.task;

import java.util.ArrayList;

/**
 * Contains list of tasks and operations to add/delete/mark/unmark tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets list of tasks
     *
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks task on the list as done
     *
     * @param number task number of task on the list to be marked as done
     */
    public void markTask(int number) {
        tasks.get(number).markTaskDone();
    }

    /**
     * Marks task on the list as undone
     *
     * @param number task number of task on the list to be unmarked
     */
    public void unmarkTask(int number) {
        tasks.get(number).unmarkTaskDone();
    }

    /**
     * Deletes task from the list
     *
     * @param number task number of task to be deleted from the list
     */
    public void deleteTask(int number) {
        tasks.remove(number);
    }

    /**
     * Adds task to the list
     *
     * @param task task to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns list of tasks with specified keyword
     *
     * @param keyword keyword to be searched
     * @return list of matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}

