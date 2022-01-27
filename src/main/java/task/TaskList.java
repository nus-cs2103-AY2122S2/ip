package task;

import utility.Input;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public Integer getSize() {
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

    public void printTasks(Input input) {
        if (tasks.size() == 0) {
            System.out.println("You currently do not have any tasks");
        } else {
            input.print("These are your tasks: ");
            for (int i = 1; i <= this.getSize(); i++) {
                Task task = this.getByNumber(i);
                input.print(String.format("%d. %s", i, task.toString()));
            }
        }
    }

}
