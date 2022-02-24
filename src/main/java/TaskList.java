import java.util.ArrayList;
import model.Task;
import model.TaskNoChangeException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>(0);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(int index, boolean isComplete) throws IndexOutOfBoundsException, TaskNoChangeException {
        if (index <= 0 || index > tasks.size()) {
            throw(new IndexOutOfBoundsException(index + ""));
        }
        Task task = tasks.get(index - 1);
        if (isComplete) {
            task.markComplete();
        } else {
            task.markIncomplete();
        }
        return task;
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                str.append("\n");
            }
            str.append(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
        return str.toString();
    }
}
