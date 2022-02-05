package duke;

import java.util.ArrayList;

/**
 * Wrapper class for list of tasks stored in List
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
     * Returns all tasks with names/descriptions that
     * contain the given search term
     *
     * @param searchTerm Search term provided by user
     * @return TaskList of tasks with descriptions that contain the given search term
     */
    public TaskList findTask(String searchTerm) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.description.contains(searchTerm)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public Task removeTaskIndex(int index) {
        return this.tasks.remove(index);
    }

    public Boolean removeTask(Task task) {
        return this.tasks.remove(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }
}
