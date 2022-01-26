package duke.tasks;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }

    public String printTaskCount() {
        return "Now you have " + tasks.size() + " task(s) in the list.";
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int index) {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!", e);
        }
    }

    public void markTask(int index) {
        try {
            tasks.get(index).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!", e);
        }
    }

    public void unmarkTask(int index) {
        try {
            tasks.get(index).setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!", e);
        }
    }

    public ArrayList<Task> findTasks(String search) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task t : tasks) {
            if (t.description.contains(search)) {
                result.add(t);
            }
        }
        return result;
    }

    public String taskListToString() {
        StringBuilder result = new StringBuilder();
        String title = tasks.isEmpty() ? "You got no task now! Start by adding new tasks."
                : "Here are the task(s) in your list:";
        result.append(title);
        for (int i = 0; i < tasks.size(); ++i) {
            int index = i + 1;
            result.append("\n  " + index + ". " + tasks.get(i).toString());
        }
        return result.toString();
    }

    public String taskListFileString() {
        StringBuilder result = new StringBuilder();
        for (Task t : tasks) {
            result.append(t.simpleString() + "\n");
        }
        return result.toString();
    }
}
