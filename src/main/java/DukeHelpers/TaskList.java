package DukeHelpers;

import DukeHelpers.Storage;
import Exceptions.DukeException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidIndexException;
import Commands.Deadline;
import Commands.Event;
import Commands.ToDo;
import Commands.Task;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }
    public TaskList() {
        this.arr = new ArrayList<Task>();
    }

    public static void addTask(Task t) {
        arr.add(t);
    }

    public static void deleteTask(String input, String ans) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < len()) {
            Task t = getTask(index);
            arr.remove(t);
            ans += "Noted. I've removed this task:\n\t\t" + t.toString() +
                    "\n\tNow you have " + numOfTasks() + " in the list.";;
        } else {
            throw new InvalidIndexException();
        }
        Storage.saveToFile(arr);
        System.out.println(ans);
    }

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

    public static void toggleMarkTask(String ans, String input) throws DukeException {
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

    public static void onTodo(String ans, String input) throws java.io.IOException {
        String desc = input.substring(5);
        ToDo t = new ToDo(desc);
        addTask(t);
        Storage.saveToFile(arr);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + numOfTasks() + " in the list.";
        System.out.println(ans);
    }

    public static void onDeadline(String ans, String input) throws java.io.IOException, InvalidDateException {
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

    public static void onEvent(String ans, String input) throws java.io.IOException, InvalidDateException {
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

    public static Task getTask(int index) {
        return arr.get(index);
    }

    public static int len() {
        return arr.size();
    }

    private static String numOfTasks() {
        return len() == 1
                ? "1 task"
                : len() + " tasks";
    }

    public static void getMatchedTasks(String input) {
        boolean isMatched = false;
        String keyword = input.substring(5);
        String ans = "\tHere are the matching tasks in your list:";
        for (int i = 0; i < len(); i++) {
            Task t = arr.get(i);
            if(t.matchDescription(keyword)) {
                ans += String.format("\n\t%d.%s", i + 1, t.toString());
                isMatched = true;
            } else {
                continue;
            }
        }
        ans = (isMatched) ? ans : "There are no matching tasks in your list!";
        System.out.println(ans);
    }

}
