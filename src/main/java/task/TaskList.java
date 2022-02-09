package task;

import java.util.ArrayList;

/**
 * The TaskList class is used to contain a list of tasks and methods to manipulate the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean hasTask(int index) {
        if (!(this.tasks.size() != 0 && index >= 0 && index < tasks.size())) {
            return false;
        }
        return true;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void removeTask(Task t) {
        this.tasks.remove(t);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> findTasks(String search) {
        ArrayList<Task> found = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.getTask(i).description.contains(search)) {
                found.add(this.getTask(i));
            }
        }
        return found;
    }

    public String displayTasks(String startMsg, ArrayList<Task> tasks, boolean isFind) {
        if (tasks.size() > 0) {
            StringBuilder sb = new StringBuilder(startMsg + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append((i + 1) + "." + tasks.get(i));
            }
            return sb.toString();
        } else {
            if (isFind) {
                return "There are no matching tasks.";
            } else {
                return "Your task list is empty.";
            }
        }
    }

    @Override
    public String toString() {
        return displayTasks("Here are the tasks in your list: ", this.tasks, false);
    }
}

