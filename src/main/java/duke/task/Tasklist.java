package duke.task;

import java.util.LinkedList;

public class Tasklist {

    private LinkedList<Task> tasks;

    public Tasklist() {
        this.tasks = new LinkedList<Task>();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getTotalTasks() {
        return this.tasks.size();
    }

    public Task mark(int index) {
        Task t = this.tasks.get(index);
        t.mark();
        return t;
    }

    public Task unmark(int index) {
        Task t = this.tasks.get(index);
        t.unmark();
        return t;
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder("Fetching all records...\n");
        if (this.tasks.size() == 0) {
            return allTasks.append("No entries found, start by adding one!\n").toString();
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            allTasks.append(i + 1).append(". ").append(this.tasks.get(i).toString()).append("\n");
        }
        return allTasks.toString();
    }
}