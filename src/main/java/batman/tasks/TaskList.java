package batman.tasks;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import batman.exception.DukeException;
import batman.exception.Error;

public class TaskList {

    private static ArrayList<Task> tasks;

    /**
     * Creates a new ArrayList to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates and stores existing tasks in ArrayList.
     *
     * @param tasks Existing ArrayList of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns current task by index.
     *
     * @param index An integer specifying an index in the list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns size of list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns an ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds tasks to list.
     *
     * @param t Task object
     * @return String object of added task.
     */
    public static String addTask(Task t) {
        try {
            tasks.add(t);
            Collections.sort(tasks);
            return printTask(t);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Missing argument(s) for tasks\n"
                    + "e.g. <task> <desc> /(at or by) <datetime>\n";
        } catch (DateTimeParseException e) {
            return "Get the date format right!\ndd/MM/yyyy HH:mm OR yyyy-MM-dd HH:mm\n";
        }
    }

    /**
     * Deletes an existing task based on the index given.
     *
     * @param index The index of an existing task in the list.
     * @return String object of deleted task and existing list.
     */
    public static String deleteTask(int index) {
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LIST_ERROR);
            }
            Task t = tasks.get(index);
            tasks.remove(index);
            return "Got it. Task removed:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
        } catch (DukeException e) {
            return printList() + e.listError();
        }
    }

    /**
     * Marks the status of the task.
     *
     * @param index The index of an existing task in the list.
     * @return String object of the task's changed status.
     */
    public static String markStatus(int index) {
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LIST_ERROR);
            }
            tasks.get(index).markDone();
            return "Alfred, mark it as done!\n  "
                    + tasks.get(index).toString() + "\n";
        } catch (DukeException e) {
            return printList() + e.listError();
        }
    }

    /**
     * Unmarks the status of the task.
     *
     * @param index The index of an existing task in the list.
     * @return String object of the task's changed status.
     */
    public static String unmarkStatus(int index) {
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LIST_ERROR);
            }
            tasks.get(index).unmarkDone();
            return "Make up your mind. Alfred, unmark it!\n  "
                    + tasks.get(index).toString() + "\n";
        } catch (DukeException e) {
            return printList() + e.listError();
        }
    }

    /**
     * Finds tasks using given keyword.
     *
     * @param keyword A word given by user.
     * @return String object of the tasks found.
     */
    public static String findTask(String keyword) {
        String result = "";
        for (Task t : tasks) {
            if (t.contains(keyword)) {
                int index = tasks.indexOf(t) + 1;
                result += index + ". " + t + "\n";
            }
        }
        if (result.isEmpty()) {
            return "No matching tasks in your list.\n";
        } else {
            return "Here are the matching tasks in your list:\n" + result;
        }
    }

    /**
     * Returns String object of all existing tasks.
     *
     * @return String object of task list.
     */
    public static String printList() {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            return "List is empty.\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }

    /**
     * Returns String object of added task.
     *
     * @param t Task object
     * @return String object of task.
     */
    private static String printTask(Task t) {
        return "Got it. Task added:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
    }
}
