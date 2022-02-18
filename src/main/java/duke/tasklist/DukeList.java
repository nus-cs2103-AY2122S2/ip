package duke.tasklist;

import java.time.LocalDate;
import java.util.List;

import duke.exceptions.DuplicateTaskException;
import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

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
     * Returns index of task if the Task is present in the current list, else return -1
     * @param t Task to check
     * @return Index of duplicate task or -1
     */
    private int isPresent(Task t) {
        boolean isInside = false;
        int count = 0;
        for (Task i : tasks) {
            count++;
            if (i instanceof Deadlines) {
                isInside = ((Deadlines) i).equals(t);
            } else if (i instanceof Events) {
                isInside = ((Events) i).equals(t);
            } else if (i instanceof ToDos) {
                isInside = ((ToDos) i).equals(t);
            }
            if (isInside) {
                return count;
            }
        }
        return -1;
    }
    /**
     * Adds a task to the list, prints add message to the console.
     * @param t Task to be added
     */
    public void add(Task t) throws DuplicateTaskException {
        assert t != null : "Task to add cannot be null";
        int x = isPresent(t);
        if (x > 0) {
            throw new DuplicateTaskException(x);
        }
        tasks.add(t);
        storage.store(tasks);
    }


    /**
     * Mark specified task as done.
     * @param x Index of task to mark
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
        tasks.remove(x - 1);
        storage.store(tasks);
    }

    private boolean checkDate(LocalDate d) {
        return d.equals(LocalDate.now());
    }

    /**
     * Prints a list of tasks due on current date to the console
     */
    public String todayTask() {
        String day = "\nDuke: Here are the tasks due today\n";
        boolean hasNoTask = true;
        for (Task t: tasks) {
            boolean isCorrectDate = true;
            boolean isDeadlineEvent = false;
            if (t instanceof Deadlines) {
                Deadlines d = (Deadlines) t;
                isDeadlineEvent = true;
                isCorrectDate = checkDate(d.getDate());
            } else if (t instanceof Events) {
                Events e = (Events) t;
                isDeadlineEvent = true;
                isCorrectDate = checkDate(e.getDate());
            }
            if (isCorrectDate && isDeadlineEvent) {
                hasNoTask = false;
                day = day + "     " + t + "\n";
            }
        }
        if (hasNoTask) {
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
