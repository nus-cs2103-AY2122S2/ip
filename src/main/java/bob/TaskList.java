package bob;

import java.util.List;

import bob.exception.BobException;
import bob.exception.InvalidIndexException;
import bob.task.Task;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getList() {
        return this.taskList;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public Task getTask(int i) {
        try {
            return this.taskList.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(Task task) throws BobException {
        this.taskList.remove(task);
    }
}
