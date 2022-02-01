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
    public boolean removeFromTaskList(Task selectedTaskToDelete) {
        return taskList.remove(selectedTaskToDelete);
    }
    public Task get(Integer i) {
        return taskList.get(i);
    }
    public void add(Task task) {
        taskList.add(task);
    }
    public Integer size() {
        return taskList.size();
    }
    public void find(String keyword) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(keyword)) {
                System.out.print(String.format("%d. %s \n", i+1, taskList.get(i).toString()));
            }
        }
    }
}
