package duke.helpers;

import java.util.ArrayList;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.ToDo;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeException;

/**
 * Represents an array of Tasks which the user can perform operations on including adding,
 * deleting, marking and getting specific Tasks.
 */
public class TaskList {

    private static ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object based on a given array of Tasks.
     *
     * @param a An array of Tasks to initialize TaskList to.
     */
    public TaskList(ArrayList<Task> a) {
        tasks = a;
    }

    /**
     * Constructs a TaskList object that stores an empty array of Tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a Task object to the TaskList.
     *
     * @param t Task object to be added.
     */
    public static void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a Task object from the TaskList.
     *
     * @param input User input that calls for the task deletion.
     * @param ans the Duke object's reply to the user.
     */
    public static String deleteTask(String input, String ans) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < len()) {
            Task t = getTask(index);
            tasks.remove(t);
            ans += "Noted. I've removed this task:\n"
                    + t.toString()
                    + "\nNow you have "
                    + getNumOfTasks()
                    + " in the list.";;
        } else {
            throw new InvalidIndexException();
        }
        Storage.saveToFile(tasks);
        return ans;
    }

    /**
     * Prints the TaskList as a formatted String.
     */
    public static String getTaskList() {
        String ans = "Here are the tasks in your list:\n";
        for (int i = 0; i < len(); i++) {
            Task t = tasks.get(i);
            if (i == len() - 1) {
                ans += String.format("%d.  %s", i + 1, t.toString());
            } else {
                ans += String.format("%d.  %s \n", i + 1, t.toString());
            }
        }
        return ans;
    }

    /**
     * Marks a specific Task in TaskList as 'marked' or 'unmarked' depending on user input
     *
     * @param input User input that calls for the task marking or un-marking.
     * @param ans the Duke object's reply to the user.
     * @throws DukeException If user specified an unknown Task.
     */
    public static String toggleMarkTask(String input, String ans) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < len()) {
            Task t = getTask(index);
            if (strArr[0].equals("mark")) {
                t.markDone();
                ans += "Nice! I've marked this task as done:\n" + t.toString();
            } else {
                t.markUndone();
                ans += "OK, I've marked this task as not done yet:\n" + t.toString();
            }
        } else {
            throw new InvalidIndexException();
        }
        Storage.saveToFile(tasks);
        return ans;
    }

    /**
     * Adds a ToDo to the TaskList
     *
     * @param input User input that calls for the ToDo to be added.
     * @param ans the Duke object's reply to the user.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     */
    public static String onTodo(String input, String ans) throws java.io.IOException {
        String desc = input.substring(5);
        ToDo t = new ToDo(desc);
        addTask(t);
        Storage.saveToFile(tasks);
        ans += "Got it. I've added this task:\n"
                + t.toString()
                + "\nNow you have "
                + getNumOfTasks()
                + " in the list.";
        return ans;
    }

    /**
     * Adds a Deadline to the TaskList
     *
     * @param input User input that calls for the Deadline to be added.
     * @param ans the Duke object's reply to the user.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     * @throws InvalidDateException If a date with an invalid format and/or time is given by user.
     */
    public static String onDeadline(String input, String ans)
            throws java.io.IOException, InvalidDateException, InvalidTimeException {
        String desc = input.substring(9, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        Deadline t = new Deadline(desc, by);
        if (!t.isValidDate()) {
            throw new InvalidDateException();
        }
        if (t.hasTime() && !t.isValidTime()) {
            throw new InvalidTimeException();
        }
        addTask(t);
        Storage.saveToFile(tasks);
        ans += "Got it. I've added this task:\n"
                + t.toString()
                + "\nNow you have "
                + getNumOfTasks()
                + " in the list.";
        return ans;
    }

    /**
     * Adds an Event to the TaskList
     *
     * @param input User input that calls for the Event to be added.
     * @param ans the Duke object's reply to the user.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     * @throws InvalidDateException If a date with an invalid format and/or time is given by user.
     */
    public static String onEvent(String input, String ans)
            throws java.io.IOException, InvalidDateException, InvalidTimeException {
        String desc = input.substring(6, input.indexOf("/at") - 1);
        String time = input.substring(input.indexOf("/at") + 4);
        Event t = new Event(desc, time);
        if (!t.isValidDate()) {
            System.out.println("date is not valid");
            throw new InvalidDateException();
        }
        if (t.hasTime() && !t.isValidTime()) {
            throw new InvalidTimeException();
        }
        addTask(t);
        Storage.saveToFile(tasks);
        ans += "Got it. I've added this task:\n"
                + t.toString()
                + "\nNow you have "
                + getNumOfTasks()
                + " in the list.";
        return ans;
    }

    /**
     * Returns specific Task based on an index.
     *
     * @param index Index of Task to be returned.
     * @return Specified Task.
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }


    /**
     * Returns number of Tasks in TaskList.
     *
     * @return number of Tasks.
     */
    public static int len() {
        return tasks.size();
    }

    /**
     * Returns formatted String description of number of Tasks in TaskList.
     *
     * @return String dictating number of Tasks.
     */
    private static String getNumOfTasks() {
        return len() == 1
                ? "1 task"
                : len() + " tasks";
    }

    /**
     * Returns a formatted string that lists all Tasks with description matching user input.
     *
     * @param input User input that calls Duke to find Tasks with descriptions containing a given keyword.
     */
    public static String getMatchedTasks(String input) {
        boolean isMatched = false;
        String keyword = input.substring(5);
        String ans = "Here are the matching tasks in your list:";
        for (int i = 0; i < len(); i++) {
            Task t = tasks.get(i);
            if (t.matchDescription(keyword)) {
                ans += String.format("\n%d.%s", i + 1, t.toString());
                isMatched = true;
            }
        }
        ans = (isMatched) ? ans : "There are no matching tasks in your list!";
        return ans;
    }

}
