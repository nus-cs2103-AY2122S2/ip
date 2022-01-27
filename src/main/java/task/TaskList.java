package task;

import utility.UI;

import java.util.ArrayList;
import java.util.List;

/**
 * List of Tasks Class
 */
public class TaskList {

    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
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

    public void printTasks(UI ui) {
        if (tasks.size() == 0) {
            System.out.println("You currently do not have any tasks");
        } else {
            ui.print("These are your tasks: ");
            for (int i = 1; i <= this.getSize(); i++) {
                Task task = this.getByNumber(i);
                ui.print(String.format("%d. %s", i, task.toString()));
            }
        }
    }

    public TaskList filterByKeyword(String keyword) {
        TaskList newOne = new TaskList();
        for(Task t : this.getTasks()) {
            if(t.getName().toLowerCase().contains(keyword)) {
                newOne.add(t);
            }
        }
        return newOne;
    }

}
