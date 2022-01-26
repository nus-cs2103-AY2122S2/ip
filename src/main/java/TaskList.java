import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException("☹ OOPS!!! Invalid task number.");
        }
        return tasks.remove(taskNo);
    }

    public void markDone(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException("☹ OOPS!!! Invalid task number.");
        }
        tasks.get(taskNo).markDone();
    }

    public void markUndone(int taskNo) throws DukeException {
        if (!isValidTaskNo(taskNo)) {
            throw new DukeException("☹ OOPS!!! Invalid task number.");
        }
        tasks.get(taskNo).markUndone();
    }

    public boolean isValidTaskNo(int taskNo) {
        if (taskNo > tasks.size() || taskNo < 0) {
            return false;
        } else {
            return true;
        }
    }

}
