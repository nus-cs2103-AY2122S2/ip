package duke;

import java.util.ArrayList;

/**
 * List of tasks
 */
public class TaskList {

    public ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class(when creating new)
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class(TaskList already exists)
     * @param list list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }
    
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void delete(int n) {
        this.tasks.remove(n - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void mark(int number) {
        tasks.get(number - 1).markAsDone();
    }

    public void unmark(int number) {
        tasks.get(number - 1).markAsUndone();
    }

    public TaskList find(String word) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for(Task task : tasks) {
            if(task.getDescription().contains(word)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    public void snooze(int number) {
        Deadline deadline = (Deadline) tasks.get(number - 1);
        deadline.by = deadline.by.plusDays(1);
    }
}
