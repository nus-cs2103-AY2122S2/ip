package task;

import java.util.ArrayList;

public class TaskList { //figure out how to process existing taskList from storage
    ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }
    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void delete(int serialNumber) {
        taskList.remove(serialNumber -1);
    }


    public String toString() {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s += (i + 1 + ". " + taskList.get(i) + "\n");
        }
        return s.trim();
    }

}
