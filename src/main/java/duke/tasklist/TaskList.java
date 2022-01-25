package duke.tasklist;

import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> list = null;

    public TaskList() {
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    public void add(Task task) {
        list.add(task);
        new Ui().echo("Got it. I have added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void delete(int pos) {
        String description = list.get(pos).toString();
        list.remove(pos);
        new Ui().echo("Noted. I've removed this task:\n" + description + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void markTask(int pos) {
        list.get(pos).markTask(true, true);
    }

    public void unMarkTask(int pos) {
        list.get(pos).markTask(false, true);
    }

    public void readList() {
        new Ui().showLine();
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
        new Ui().showLine();
    }

    public void fetchData(ArrayList<Task> data) {
        list.clear();
        list.addAll(data);
    }
}
