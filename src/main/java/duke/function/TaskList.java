package duke.function;

import java.util.List;
import java.util.ArrayList;

import duke.task.Task;

public class TaskList {

    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    List<Task> getTasks() {
        return this.tasks;
    }

    public Integer size() {
        return this.tasks.size();
    }

    public Task getByNumber(int number) {
        return this.tasks.get(number - 1);
    }

    public Task deleteByNumber(int number) {
        return tasks.remove(number - 1);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

}
