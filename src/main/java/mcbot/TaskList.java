package mcbot;

import java.util.ArrayList;

import mcbot.task.Task;

public class TaskList {
    
    private ArrayList<Task> taskList;
    
    public TaskList() {
    }
    
    public TaskList(ArrayList<Task> arrayList) {
        taskList = arrayList;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public void remove(int i) {
        taskList.remove(i);
    }

    public void find(String taskName, Ui ui) {
        boolean anyMatch = false;
        boolean isHeaderPrinted = false;
        for (Task t : taskList) {
            if (t.getTaskName().contains(taskName)) {
                anyMatch = true;
                if (!isHeaderPrinted && anyMatch) {
                    ui.printFind();
                    isHeaderPrinted = true;
                }
                ui.printTask(t);
            }
        }
        if (!anyMatch) {
            ui.noMatch();
        }
    }
}
