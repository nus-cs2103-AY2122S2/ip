package dazz;

import dazz.exception.InvalidTaskIndexException;
import dazz.task.Task;
import java.util.List;

public class TaskList {
    protected List<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public void add(Task task) { taskList.add(task); }

    public void mark(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            this.getTask(position).setDone();
        }
    }

    public void unmark(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            this.getTask(position).setUndone();
        }
    }

    public void delete(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            this.taskList.remove(--position);
        }
    }

    public Task getTask(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            return this.taskList.get(--position);
        }

    }

    public int getSize() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

}