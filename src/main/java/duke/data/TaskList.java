package duke.data;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();

    /** Return the size of the TaskList.  */
    public int size() {
        return this.list.size();
    }

    /** Get a Task from the list by the index of the task.  */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Add a task to the list.
     * @param task The task you want to add to the list.
     **/
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Delete a task from the list.
     * @param task The task you want to add to the list.
     **/
    public void remove(Task task) {
        list.remove(task);
    }

    public String search(String prefix) {
        String result = "";
        int count = 0;
        for (Task t : list) {
            if (t.match(prefix)) {
                result = result + t + "\n";
                count++;
            }
        }
        return result + "found " + count + " matching result(s).";
    }

    public String searchTime(String prefix) {
        prefix = Task.identifyTime(prefix);
        String result = "";
        int count = 0;
        for (Task t : list) {
            if (t.matchTime(prefix)) {
                result = result + t + "\n";
                count++;
            }
        }
        if (result.length() < 1) {
            return "You don't have any tasks on that day!";
        }
        return result + "found " + count + " task(s).";
    }

    /** Listing out all the tasks in the list.  */
    public String listing() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += (i + 1) + ".";
            s += list.get(i) + "\n";
        }
        if (s.length() <= 1) {
            s = "You have a empty list!";
        }
        return s;
    }
}
