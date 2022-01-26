package duke.helpers;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidIndexException;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.ToDo;
import duke.commands.Task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> arr) {
        this.tasks = arr;
    }
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public static void addTask(Task t) {
        tasks.add(t);
    }

    public static void deleteTask(String input, String ans) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < len()) {
            Task t = getTask(index);
            tasks.remove(t);
            ans += "Noted. I've removed this task:\n\t\t" + t.toString() +
                    "\n\tNow you have " + getNumOfTasks() + " in the list.";;
        } else {
            throw new InvalidIndexException();
        }
        Storage.saveToFile(tasks);
        System.out.println(ans);
    }

    public static void getTaskList() {
        String ans = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < len(); i++) {
            Task t = tasks.get(i);
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
        Storage.saveToFile(tasks);
        System.out.println(ans);
    }

    public static void onTodo(String ans, String input) throws java.io.IOException {
        String desc = input.substring(5);
        ToDo t = new ToDo(desc);
        addTask(t);
        Storage.saveToFile(tasks);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + getNumOfTasks() + " in the list.";
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
        Storage.saveToFile(tasks);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + getNumOfTasks() + " in the list.";
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
        Storage.saveToFile(tasks);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + getNumOfTasks() + " in the list.";
        System.out.println(ans);
    }



    public static Task getTask(int index) {
        return tasks.get(index);
    }


    public static int len() {
        return tasks.size();
    }

    private static String getNumOfTasks() {
        return len() == 1
                ? "1 task"
                : len() + " tasks";
    }

}
