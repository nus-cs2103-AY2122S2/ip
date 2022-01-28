package duke;
import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    ArrayList<Task> tasks;

    /** Instantiates an empty task list */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Instantiates a list of tasks */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String addTask(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %s task(s) in the list",
                task, tasks.size());
    }

    public String deleteItem(int itemNumber) {
        Task task = tasks.remove(itemNumber - 1);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %s task(s) in the list",
                task, tasks.size());
    }

    public String markItem(int itemNumber) {
        Task task = tasks.get(itemNumber - 1);
        task.markAsDone();
        return "Nice! I've marked this as done:\n  " + task;
    }

    public String unmarkItem(int itemNumber) {
        Task task = tasks.get(itemNumber - 1);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    public boolean isValidItemNumber(int itemNum) {
        return itemNum > 0 && itemNum <= tasks.size();
    }

    public int size() {
        return tasks.size();
    }

    public String listItems() {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            sb.append("There is nothing in the list!");
        }
        
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i + ". ").append(tasks.get(i - 1)).append("\n");
        }

        return sb.toString();
    }

    
}
