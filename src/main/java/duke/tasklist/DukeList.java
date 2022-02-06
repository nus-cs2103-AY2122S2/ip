package duke.tasklist;

import java.time.LocalDate;
import java.util.List;

import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;

public class DukeList {

    private List<Task> tasks;
    private Storage storage;

    /**
     * Constructor to load a previously stored list of tasks into a new instance of
     * DukeList using the Storage load method
     * @param s Instance of Storage
     */
    public DukeList(Storage s) {
        this.tasks = s.load();
        this.storage = s;
    }

    /**
     * Adds a task to the list, prints add message to the console.
     * @param t Task to be added
     */
    public void add(Task t) {
        tasks.add(t);
        storage.store(tasks);
    }


    /**
     * Mark specified task as done.
     * @param x index of task to mark
     */
    public void mark(int x) {
        tasks.get(x - 1).mark();
        storage.store(tasks);
    }

    /**
     * Unmark specified task.
     * @param x The task number
     */
    public void unmark(int x) {
        tasks.get(x - 1).unmark();
        storage.store(tasks);
    }

    /**
     * Returns a String representation of the list, the indexes as well as the tasks, or NO TASK if empty.
     * @return String representation of list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "NO TASKS\n";
        }
        String ans = "";
        int y = 1;
        for (Task x : tasks) {
            ans += y + ". " + x + "\n";
            y++;
        }
        return ans;
    }



    /**
     * Deletes specified task from the list
     * @param x Index of task in list to delete
     */
    public void delete(int x) {
        Task t = tasks.get(x - 1);
        tasks.remove(x - 1);
        storage.store(tasks);
    }

    /**
     * Prints a list of tasks due on current date to the console
     */
    public String todayTask() {
        LocalDate cur = LocalDate.now();
        String day = "\nDuke: Here are the tasks due today\n";
        boolean b = true;
        for (Task t: tasks) {
            if (t instanceof Deadlines) {
                Deadlines d = (Deadlines) t;
                if (d.getDate().isEqual(cur)) {
                    b = false;
                    day = day + "     " + t + "\n";
                }
            } else if (t instanceof Events) {
                Events e = (Events) t;
                if (e.getDate().isEqual(cur)) {
                    b = false;
                    day = day + "     " + t + "\n";
                }
            }
        }
        if (b) {
            day += "      NO TASK DUE TODAY";
        }
        return day;
    }

    /**
     * This method finds and prints out the tasks from the list that matches the input keyword
     * @param arg
     */
    public String findTasks(String arg) {
        String found = "\nDuke: Here are the matching tasks in your list\n";
        int n = 0;
        for (Task t: tasks) {
            if (t.find(arg)) {
                n += 1;
                found = found + "      " + n + "."
                        + t + "\n";
            }
        }
        if (n == 0) {
            found = "\nDuke: No task matches your keyword. Try again?\n";
        }
        return found;
    }

    /**
     * returns the Task at the given index in the DukeList
     * @param index index of the Task to return
     * @return a Task instance
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of elements in the DukeList
     * @return No of elements in DukeList
     */
    public int getSize() {
        return tasks.size();
    }

}
