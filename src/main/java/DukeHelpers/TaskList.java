package DukeHelpers;

import Exceptions.DukeException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidIndexException;
import Commands.Deadline;
import Commands.Event;
import Commands.ToDo;
import Commands.Task;
import java.util.ArrayList;

/**
 * Represents an array of Tasks which the user can perform operations on including adding,
 * deleting, marking and getting specific Tasks.
 */
public class TaskList {

    private static ArrayList<Task> arr;

    /**
     * Constructs a TaskList object based on a given array of Tasks.
     *
     * @param a An array of Tasks to initialize TaskList to.
     */
    public TaskList(ArrayList<Task> a) {
        arr = a;
    }

    /**
     * Constructs a TaskList object that stores an empty ArrayList<Task>.
     */
    public TaskList() {
        arr = new ArrayList<>();
    }

    /**
     * Adds a Task object to the TaskList.
     *
     * @param t Task object to be added.
     */
    public static void addTask(Task t) {
        arr.add(t);
    }

    /**
     * Deletes a Task object from the TaskList.
     *
     * @param input User input that calls for the task deletion.
     * @param ans the Duke object's reply to the user.
     */
    public static void deleteTask(String input, String ans) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < len()) {
            Task t = getTask(index);
            arr.remove(t);
            ans += "Noted. I've removed this task:\n\t\t" + t.toString() +
                    "\n\tNow you have " + numOfTasks() + " in the list.";
        } else {
            throw new InvalidIndexException();
        }
        Storage.saveToFile(arr);
        System.out.println(ans);
    }

    /**
     * Prints the TaskList as a formatted String.
     */
    public static void getTaskList() {
        String ans = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < len(); i++) {
            Task t = arr.get(i);
            if (i == len() - 1) {
                ans += String.format("\t%d.%s", i + 1, t.toString());
            } else {
                ans += String.format("\t%d.%s \n", i + 1, t.toString());
            }
        }
        System.out.println(ans);
    }

    /**
     * Marks a specific Task in TaskList as 'marked' or 'unmarked' depending on user input
     *
     * @param input User input that calls for the task marking or un-marking.
     * @param ans the Duke object's reply to the user.
     * @throws DukeException If user specified an unknown Task.
     */
    public static void toggleMarkTask(String input, String ans) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < len()) {
            Task t = getTask(index);
            if (strArr[0].equals("mark")) {
                t.markDone();
                ans += "Nice! I've marked this task as done:\n\t\t" + t.toString();
            } else {
                t.markUndone();
                ans += "OK, I've marked this task as not done yet:\n\t\t" + t.toString();
            }
        } else {
            throw new InvalidIndexException();
        }
        Storage.saveToFile(arr);
        System.out.println(ans);
    }

    /**
     * Adds a ToDo to the TaskList
     *
     * @param input User input that calls for the ToDo to be added.
     * @param ans the Duke object's reply to the user.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     */
    public static void onTodo(String input, String ans) throws java.io.IOException {
        String desc = input.substring(5);
        ToDo t = new ToDo(desc);
        addTask(t);
        Storage.saveToFile(arr);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + numOfTasks() + " in the list.";
        System.out.println(ans);
    }

    /**
     * Adds a Deadline to the TaskList
     *
     * @param input User input that calls for the Deadline to be added.
     * @param ans the Duke object's reply to the user.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     * @throws InvalidDateException If a date with an invalid format and/or time is given by user.
     */
    public static void onDeadline(String input, String ans) throws java.io.IOException, InvalidDateException {
        String desc = input.substring(9, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        Deadline t = new Deadline(desc, by);
        if (!t.isValidDate() && !t.isValidTime()) {
            throw new InvalidDateException();
        }
        addTask(t);
        Storage.saveToFile(arr);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + numOfTasks() + " in the list.";
        System.out.println(ans);
    }

    /**
     * Adds an Event to the TaskList
     *
     * @param input User input that calls for the Event to be added.
     * @param ans the Duke object's reply to the user.
     * @throws java.io.IOException If I/O operations fail or is interrupted.
     * @throws InvalidDateException If a date with an invalid format and/or time is given by user.
     */
    public static void onEvent(String input, String ans) throws java.io.IOException, InvalidDateException {
        String desc = input.substring(6, input.indexOf("/at") - 1);
        String time = input.substring(input.indexOf("/at") + 4);
        Event t = new Event(desc, time);
        if (!t.isValidDate() && !t.isValidTime()) {
            throw new InvalidDateException();
        }
        addTask(t);
        Storage.saveToFile(arr);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + numOfTasks() + " in the list.";
        System.out.println(ans);
    }

    /**
     * Returns specific Task based on an index.
     *
     * @param index Index of Task to be returned.
     * @return Specified Task.
     */
    public static Task getTask(int index) {
        return arr.get(index);
    }


    /**
     * Returns number of Tasks in TaskList.
     *
     * @return number of Tasks.
     */
    public static int len() {
        return arr.size();
    }

    /**
     * Returns formatted String description of number of Tasks in TaskList.
     *
     * @return String dictating number of Tasks.
     */
    private static String numOfTasks() {
        return len() == 1
                ? "1 task"
                : len() + " tasks";
    }

    /**
     * Returns a formatted string that lists all Tasks with description matching user input.
     *
     * @param input User input that calls Duke to find Tasks with descriptions containing a given keyword.
     */
    public static void getMatchedTasks(String input) {
        boolean isMatched = false;
        String keyword = input.substring(5);
        String ans = "\tHere are the matching tasks in your list:";
        for (int i = 0; i < len(); i++) {
            Task t = arr.get(i);
            if(t.matchDescription(keyword)) {
                ans += String.format("\n\t%d.%s", i + 1, t.toString());
                isMatched = true;
            }
        }
        ans = (isMatched) ? ans : "There are no matching tasks in your list!";
        System.out.println(ans);
    }

}
