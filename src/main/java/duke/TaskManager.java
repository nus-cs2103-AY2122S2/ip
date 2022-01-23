package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    TaskManager() {
        this.taskList = new ArrayList<>();
    }

    TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    ArrayList<Task> getTasks() {
        return this.taskList;
    }

    int countTasks() {
        return this.taskList.size();
    }

    public String list() {
        if (this.countTasks() == 0) {
            return "You have no tasks.";
        }
        String response = "Here are the tasks in your list:\n";
        for (int i = 1; i <= this.countTasks(); i++) {
            response += (i + ". " + this.taskList.get(i - 1));
            if (i != this.countTasks()) {
                response += "\n";
            }
        }
        return response;
    }

    public String mark(int index) {
        String response = "";
        try {
            Task taskToMark = this.taskList.get(index);
            taskToMark.mark();
            response += "Nice! I've marked this task as done:\n";
            response += "    " + this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        return response;
    }

    public String delete(int index) {
        String response = "";
        try {
            Task taskToDelete = this.taskList.get(index);
            this.taskList.remove(index);
            response += "Noted. I've removed this task:\n";
            response += "    " + taskToDelete + "\n";
            response += String.format("Now you have %d tasks in the list.", this.countTasks());
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        return response;
    }

    public String add(Task task) {
        this.taskList.add(task);
        String response = "Got it. I've added this task:\n";
        response += "    " + task + "\n";
        response += String.format("Now you have %d tasks in the list.", this.countTasks());
        return response;
    }

    public String find(String description) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task: this.taskList) {
            if (task.getDescription().contains(description)) {
                tasks.add(task);
            }
        }
        if (tasks.size() == 0) {
            return "There are no tasks matching this description.";
        }
        String response = "Here are the tasks matching the description:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            response += (i + ". " + tasks.get(i - 1));
            if (i != tasks.size()) {
                response += "\n";
            }
        }
        return response;
    }

    public String unmark(int index) {
        String response = "";
        try {
            Task taskToMark = this.taskList.get(index);
            taskToMark.unmark();
            response += "OK, I've marked this task as not done yet:\n";
            response += "    " + this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        return response;
    }
}

