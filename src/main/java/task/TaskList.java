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



}