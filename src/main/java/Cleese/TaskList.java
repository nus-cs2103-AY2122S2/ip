package Cleese;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(String.format("%d. %s \n", i+1, taskList.get(i).toString()));
        }
    }
    public void add(Task task) {
        taskList.add(task);
    }
    public boolean removeFromTaskList(Task selectedTaskToDelete) {
        return taskList.remove(selectedTaskToDelete);
    }
    public Task get(Integer i) {
        return taskList.get(i);
    }
    public Integer size() {
        return taskList.size();
    }
}
