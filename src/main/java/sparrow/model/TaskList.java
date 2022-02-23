package sparrow.model;

import java.util.ArrayList;

import sparrow.logic.task.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    /**
     * Constructs a task list.
     * @param t An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> t) {
        tasks = t;
    }

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void mark(int index) {
        tasks.get(index).mark();
    }

    public void unmark(int index) {
        tasks.get(index).unmark();
    }
    /**
     * Deletes a task.
     * @param index The index of the task in the task list.
     */
    public Task delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }
    /**
     * Changes the priority of a task.
     * @param i The index of the task in the task list.
     * @param p The priority to set the task to.
     */
    public void prioritise(int i, Priority p) {
        tasks.get(i).prioritise(p);
    }
    /**
     * Filters the tasks according to a keyword.
     */
    public TaskList filter(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }
}
