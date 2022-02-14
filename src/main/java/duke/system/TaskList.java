package duke.system;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * The TaskList class manages the operations of the
 * arraylist of tasks
 *
 * @author  Melvin Chan Zijun
 */
public class TaskList {
    /**
     * The arraylist of tasks
     */
    private final ArrayList<Task> tasks;

    /**
     * Overloaded constructor.
     *
     * @param tasks - arraylist of tasks loaded
     *                from storage
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Overloaded constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * This method adds tasks into the tasklist.
     *
     * @param task - task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * This method deletes the task at given index
     * from the tasklist.
     *
     * @param i - the acutal index of task in
     *            the arraylist is i - 1
     * @throws DukeException - if tasklist is empty or
     *                         index out of range
     */
    public void delete(int i) throws DukeException {
        if (!this.get(i).isEmptyTask()) {
            this.tasks.remove(i - 1);
        } else {
            throw new DukeException("No such task found!");
        }
    }

    /**
     * This method gets the task at given index
     * from the tasklist.
     *
     * @param i - the acutal index of task in
     *            the arraylist is i - 1
     * @return Task - task at index
     * @throws DukeException - if tasklist is empty or
     *                         index out of range
     */
    public Task get(int i) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("TaskList is empty!");
        } else if (i < 1 || i > tasks.size()) {
            throw new DukeException("Requested task does not exist!");
        } else {
            return tasks.get(i - 1);
        }
    }

    /**
     * Returns the size of the arraylist
     *
     * @return int size of arraylist
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Consolidates all tasks in the task list into
     * the format to be displayed to the user.
     *
     * @return String all tasks in task list
     * @throws DukeException if arraylist is empty or
     *                       i is out of range
     */
    public String tasksAsString() throws DukeException {
        String taskAsString = "";
        int i = 0;
        while (i < this.tasks.size()) {
            String space = "     ";
            Task curr = this.get(i + 1);
            taskAsString += space + (i + 1) + ". " + curr + "\n";
            i++;
        }
        return taskAsString;
    }

    /**
     * Consolidates all tasks in the arraylist into
     * the format to be stored as data.
     *
     * @return String all tasks in task list in their data form
     * @throws DukeException if arraylist is empty or
     *                       i is out of range
     */
    public String taskAsData() throws DukeException {
        String taskAsData = "";
        int i = 0;
        while (i < this.tasks.size()) {
            Task curr = this.get(i + 1);
            String isMarked = curr.isMarked()
                    ? "T"
                    : "F";
            taskAsData += curr.getPrefix() + "/"
                    + isMarked + "/"
                    + curr.getName() + "/"
                    + curr.getDate() + "/"
                    + curr.getTime() + "\n";
            i++;
        }
        return taskAsData;
    }

    /**
     * Marks an incomplete task as complete.
     *
     * @param i index of task as shown from the list command
     * @throws DukeException if arraylist is empty,
     *                       i is out of range or
     *                       task is already marked
     */
    public void mark(int i) throws DukeException {
        if (this.isEmpty()) {
            throw new DukeException("No tasks in TaskList!");
        } else if (get(i).isMarked()) {
            throw new DukeException("Task is already marked!");
        } else {
            get(i).mark();
        }
    }

    /**
     * Marks a complete task as incomplete.
     *
     * @param i index of task as shown from the list command
     * @throws DukeException if arraylist is empty,
     *                       i is out of range or
     *                       task is already unmarked
     */
    public void unmark(int i) throws DukeException {
        if (this.isEmpty()) {
            throw new DukeException("No tasks in TaskList!");
        } else if (get(i).isMarked()) {
            throw new DukeException("Task is already unmarked!");
        } else {
            get(i).unmark();
        }
    }

    /**
     * This method consolidates all tasks in the arraylist
     * that contains the given keyword into a String in the
     * format to be displayed to the user.
     *
     * @param keyword input from user
     * @return String all the tasks containing the keyword
     * @throws DukeException if arraylist is empty or
     *                       i is out of range
     */
    public String find(String keyword) throws DukeException {
        String taskAsString = "";
        int i = 0;
        while (i < this.tasks.size()) {
            String space = "     ";
            Task curr = this.get(i + 1);
            if (curr.getName().contains(keyword)) {
                taskAsString += space + (i + 1) + ". " + curr + "\n";
            }
            i++;
        }
        return taskAsString;
    }

    /**
     * Removes all tasks in the arraylist.
     * Use with caution.
     */
    public void clear() {
        this.tasks.clear();
    }

    /**
     * Returns a boolean value of whether the arraylist is empty.
     *
     * @return true if list is empty
     *         and false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
