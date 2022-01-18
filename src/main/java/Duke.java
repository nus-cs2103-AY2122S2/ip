import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private ArrayList<Task> arr;

    public Duke(){
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

    private boolean isType(String s, String type) {
        boolean res = false;
        switch (type) {
            case "bye":
                res = s.equals("bye");
                break;
            case "list":
                res = s.equals("list");
                break;
            case "toggleMark":
                res = Pattern.matches("mark \\d+|unmark \\d+", s);
                break;
            case "todo":
                res = Pattern.matches("todo .+", s);
                break;
            case "deadline":
                res = Pattern.matches("deadline .+ /by .+", s);
                break;
            case "event":
                res = Pattern.matches("event .+ /at .+", s);
                break;
        }
        return res;
    }

    private void answer(String input) {
        String ans = "\t";
        if (isType(input, "bye")) {
            ans += "Bye. Hope to see you again soon!";
        } else if (isType(input, "list")) {
            ans += "Here are the tasks in your list:\n";
            for (int i = 0; i < arr.size(); i++) {
                Task t = arr.get(i);
                if (i == arr.size() - 1) {
                    ans += String.format("\t%d.%s", i + 1, t.toString());
                } else {
                    ans += String.format("\t%d.%s \n", i + 1, t.toString());
                }
            }
        } else if (isType(input, "toggleMark")) {
            String[] strArr = input.split(" ");
            int index = Integer.valueOf(strArr[1]) - 1;
            if (index >= 0 && index < arr.size()) {
                Task t = arr.get(index);
                if (strArr[0].equals("mark")) {
                    t.markDone();
                    ans += "Nice! I've marked this task as done: \n\t\t" + t.toString();
                } else {
                    t.markUndone();
                    ans += "OK, I've marked this task as not done yet: \n\t\t" + t.toString();
                }
            } else {
                ans = "\tInvalid index given!";
            }
        } else if (isType(input, "todo")) {
            String desc = input.substring(5);
            Task t = new ToDo(desc);
            addTask(t);
            ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                    "\n\tNow you have " + numOfTasks() + " in the list.";
        } else if (isType(input, "deadline")) {
            String desc = input.substring(9, input.indexOf("/by") - 1);
            String by = input.substring(input.indexOf("/by") + 4);
            Task t = new Deadline(desc, by);
            addTask(t);
            ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                    "\n\tNow you have " + numOfTasks() + " in the list.";
        } else if (isType(input, "event")) {
            String desc = input.substring(6, input.indexOf("/at") - 1);
            String time = input.substring(input.indexOf("/at") + 4);
            Task t = new Event(desc, time);
            addTask(t);
            ans += "Got it. I've added this task:\n\t\t" + t.toString() +
                    "\n\tNow you have " + numOfTasks() + " in the list.";
        } else {
            Task task = new Task(input);
            addTask(task);
            ans += "added: " + input;

        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
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

