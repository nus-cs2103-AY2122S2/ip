package task;

import util.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> list;
    public Ui ui;

    public TaskList() {
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
    }

    public void add(Task t) {
        list.add(t);
        ui.reply(addTask(this.get(this.size() - 1), this.size()));
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void remove(int index) {
        list.remove(index);
    }

    /**
     * creates command message to inform the user that item has been deleted
     * @param item index of the item in the list to be deleted
     */

    public void deleteItem(String item) {
        try {
            int index = Integer.parseInt(item);
            Task t = this.get(index - 1);
            this.remove(index - 1);
            ui.reply(removeTask(t, this.size()));
        } catch (IndexOutOfBoundsException e) {
            ui.reply("You can't do that! It's not on the list!");
        }
    }

    /**
     * removes the task from the list
     * @param task task to be removed
     * @param total total number of tasks after removing this task from the list
     * @return String message to tell the user that item has been deleted
     */

    public String removeTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Less work for you then less work for me then. I've removed this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;

        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }
        return firstLine + secondLine + thirdLine;

    }

    /**
     * adds the task to the list
     * @param task task to be added
     * @param total total number of tasks after adding this task to the list
     * @return
     */

    public String addTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Ah sure. I've added this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;
        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }

        return firstLine + secondLine + thirdLine;

    }

    public void find(String item) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        String tab = "    ";

        for (Task t: this.list) {
            if (t.description.contains(item)) {
                foundTasks.add(t);

            }
        }

        String firstLine = "Here are the matching tasks in you list:\n" + tab;
        String lists = "";
        for (int i = 0; i < foundTasks.size(); i++) {
            if (i != 0) {
                lists += "\n" + tab;
            }
            lists += String.format("%d. %s", i + 1, foundTasks.get(i).toString());

        }

        ui.reply(firstLine + lists);
    }



}