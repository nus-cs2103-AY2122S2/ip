package yalebot;

import yalebot.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTo(Task taskItem) {
        list.add(taskItem);
    }

    public String listOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i+1 + "."
                    + list.get(i) + "\n";
        }
        return output;
    }

    public Task getItem(int itemNo) {
        return list.get(itemNo);
    }

    public int getSize() {
        return list.size();
    }

    public void deleteItem(int itemNo) {
        list.remove(itemNo);
    }
}

