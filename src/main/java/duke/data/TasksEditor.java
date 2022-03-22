package duke.data;

import duke.data.task.Task;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TasksEditor {
    private Deque<TaskList.Memento> stateHistory;
    private TaskList tasks;

    public TasksEditor(TaskList tasks) {
        this.stateHistory = new LinkedList<>();
        this.tasks = tasks;
    }

    public void add(Task task) {
        stateHistory.add(tasks.takeSnapshot());
        tasks.add(task);
    }

    public Task delete(int index) {
        stateHistory.add(tasks.takeSnapshot());
        return tasks.delete(index);
    }

    public Task unmark(int index) {
        stateHistory.add(tasks.takeSnapshot());
        return tasks.unmark(index);
    }

    public Task mark(int index) {
        stateHistory.add(tasks.takeSnapshot());
        return tasks.mark(index);
    }

    public int getSize() {
        return tasks.getSize();
    }

    public List<Task> toList() {
        return tasks.toList();
    }

    public String toString() {
        return tasks.toString();
    }

    public boolean undo() {
        if (stateHistory.isEmpty()) {
            return false;
        }
        tasks.restore(stateHistory.pollLast());
        return true;
    }
}
