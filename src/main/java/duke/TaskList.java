package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> ls;

    public TaskList(List<Task> ls) {
        this.ls = ls;
    }

    public TaskList() {
        this.ls = new ArrayList<>();
    }

    public void listTasks() {
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + ". " + ls.get(i));
        }
    }

    public void findAndPrintTasks(String keyword) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getName().contains(keyword)) {
                System.out.println((i + 1) + ". " + ls.get(i));
            }
        }
    }

    public Task setDone(int index) {
        ls.get(index).setDone();
        return ls.get(index);
    }

    public Task setUndone(int index) {
        ls.get(index).setUndone();
        return ls.get(index);
    }

    public void addTask(Task toAdd) {
        ls.add(toAdd);
    }

    public Task deleteTask(int index) {
        Task toRemove = ls.get(index);
        ls.remove(index);
        return toRemove;
    }

    public int getSize() {
        return ls.size();
    }

    public List<Task> getList() {
        return this.ls;
    }

}
