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

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void printTasks(Ui ui) {
        if (tasks.size() == 0) {
            System.out.println("You currently do not have any tasks *quack*, please add some more");
        } else {
            ui.print("These are your tasks *quack*:");
            for (int i = 1; i <= this.size(); i++) {
                Task task = this.getByNumber(i);
                ui.print(String.format("%d. %s", i, task.toString()));
            }
        }
    }

    public TaskList filterByKeyword(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task task : this.getTasks()) {
            if (task.getName().toLowerCase().contains(keyword)) {
                newTaskList.add(task);
            }
        }
        return newTaskList;
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
