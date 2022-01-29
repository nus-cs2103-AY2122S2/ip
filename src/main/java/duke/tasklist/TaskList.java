package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        taskList = list;
    }

    public boolean add(Task task) {
        return taskList.add(task);
    }

    public Task remove(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.remove(index);
        }
        return null;
    }

    public List<Task> list() {
        List<Task> copy = new ArrayList<>();
        for (Task task : taskList) {
            copy.add(task);
        }
        return copy;
    }

    public int size() {
        return taskList.size();
    }

    public Task mark(int index) {
        Task task = taskList.get(index);
        task.mark();
        return task;
    }

    public Task unmark(int index) {
        Task task = taskList.get(index);
        task.unmark();
        return task;
    }
}
