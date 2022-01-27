import java.util.ArrayList;
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

    public void list() {
        System.out.println("\tHere are the tasks in your list:");
        if (taskList.size() == 0) {
            System.out.println("\tYou have no task in your list.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println("\t" + (i + 1) + ". " + task);
            }
        }
    }




}