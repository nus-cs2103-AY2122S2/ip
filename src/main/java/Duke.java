import Exceptions.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private ArrayList<Task> arr;

    public Duke() {
        this.arr = new ArrayList<Task>();
    }

    private String numOfTasks() {
        return arr.size() == 1
                ? "1 task"
                : arr.size() + " tasks";
    }

    private void addTask(Task t) {
        this.arr.add(t);
    }

    private void deleteTask(Task t) { this.arr.remove(t); }

    private boolean isCommand(String s, Command command) throws DukeException {
        boolean res = false;
        boolean missingDesc = false;
        boolean missingTime = false;
        switch (command) {
            case BYE:
                res = s.equals("bye");
                break;
            case LIST:
                res = s.equals("list");
                break;
            case DELETE:
                res = Pattern.matches("delete \\d+", s);
                break;
            case TOGGLEMARK:
                res = Pattern.matches("mark \\d+|unmark \\d+", s);
                break;
            case TODO:
                res = Pattern.matches("todo .+", s);
                missingDesc = !res && s.equals("todo ");
                break;
            case DEADLINE:
                res = Pattern.matches("deadline .+ /by .+", s);
                missingDesc = !res && Pattern.matches("deadline\\s+|deadline\\s+/by.*", s);
                missingTime = !res && !missingDesc && Pattern.matches("deadline .+", s);
                break;
            case EVENT:
                res = Pattern.matches("event .+ /at .+", s);
                missingDesc = !res && Pattern.matches("event\\s+|event\\s+/at.*", s);
                missingTime = !res && !missingDesc && Pattern.matches("event .+", s);
                break;
        }
        if (missingDesc) { throw new EmptyDescriptionException(command.toString()); }
        if (missingTime) { throw new EmptyTimeException(command.toString()); }
        return res;
    }

    private void onBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    private void onList(String ans) {
        ans += "Here are the tasks in your list:\n";
        for (int i = 0; i < arr.size(); i++) {
            Task t = arr.get(i);
            if (i == arr.size() - 1) {
                ans += String.format("\t%d.%s", i + 1, t.toString());
            } else {
                ans += String.format("\t%d.%s \n", i + 1, t.toString());
            }
        }
        System.out.println(ans);
    }

    private void onDelete(String ans, String input) throws DukeException, IOException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < arr.size()) {
            Task t = arr.get(index);
            deleteTask(t);
            ans += "Noted. I've removed this task:\n\t\t" + t.toString() +
                    "\n\tNow you have " + numOfTasks() + " in the list.";;
        } else {
            throw new InvalidIndexException();
        }
        System.out.println(ans);
    }

    private void onToggleMark(String ans, String input) throws DukeException, IOException {
        String[] strArr = input.split(" ");
        int index = Integer.valueOf(strArr[1]) - 1;
        if (index >= 0 && index < arr.size()) {
            Task t = arr.get(index);
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
        System.out.println(ans);
    }

    private void onTodo(String ans, String input) throws java.io.IOException {
        String desc = input.substring(5);
        ToDo t = new ToDo(desc);
        addTask(t);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + numOfTasks() + " in the list.";
        System.out.println(ans);
    }

    private void onDeadline(String ans, String input) throws java.io.IOException, InvalidDateException {
        String desc = input.substring(9, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        Deadline t = new Deadline(desc, by);
        if (!t.isValidDate() && !t.isValidTime()) {
            throw new InvalidDateException();
        }
        addTask(t);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + numOfTasks() + " in the list.";
        System.out.println(ans);
    }

    private void onEvent(String ans, String input) throws java.io.IOException, InvalidDateException {
        String desc = input.substring(6, input.indexOf("/at") - 1);
        String time = input.substring(input.indexOf("/at") + 4);
        Event t = new Event(desc, time);
        if (!t.isValidDate() && !t.isValidTime()) {
            throw new InvalidDateException();
        }
        addTask(t);
        ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                "\n\tNow you have " + numOfTasks() + " in the list.";
        System.out.println(ans);
    }

    private void answer(String input) {
        String ans = "\t";
        try {
            if (isCommand(input, Command.BYE)) {
                onBye();
            } else if (isCommand(input, Command.LIST)) {
                onList(ans);
            } else if (isCommand(input, Command.DELETE)) {
                onDelete(ans, input);
            } else if (isCommand(input, Command.TOGGLEMARK)) {
                onToggleMark(ans, input);
            } else if (isCommand(input, Command.TODO)) {
                onTodo(ans, input);
            } else if (isCommand(input, Command.DEADLINE)) {
                onDeadline(ans, input);
            } else if (isCommand(input, Command.EVENT)) {
                onEvent(ans, input);
            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException | java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            duke.answer(userInput);
        }
        sc.close();

    }
}