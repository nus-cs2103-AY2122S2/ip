import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    public static void main(String[] args) throws DukeException {
        String name = "Enkel";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String frame = "______________________________________________";
        System.out.println(frame + "\nHello! I\'m " + name + "\nWhat can I do for you?\n" + frame + "\n");

        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> tasks = new ArrayList<Task>();
        while (true) {
            input = sc.nextLine();
            String[] splited = input.split(" ", 2);
            String firstWord = splited[0];
            String remaining = "";
            if (splited.length == 2) {
                remaining = splited[1];
            }
            System.out.println(frame);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" + frame);
                break;
            } else if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println("There are no tasks in your list~");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else if (input.matches("delete \\d*[1-9]+\\d*")) {
                int i = Integer.parseInt(remaining);
                if (i <= tasks.size()) {
                    System.out.println("Noted. I've removed this task:\n" + tasks.get(i - 1));
                    tasks.remove(i - 1);
                } else {
                    System.out.println("Index is invalid");
                }
            } else if (input.matches("(un)?mark \\d*[1-9]+\\d*")) {
                int i = Integer.parseInt(remaining);
                if (firstWord.equals("unmark")) {
                    if (i <= tasks.size()) {
                        tasks.get(i - 1).markNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n"
                                + tasks.get(i - 1));
                    } else {
                        System.out.println("Index is invalid");
                    }
                } else {
                    if (i <= tasks.size()) {
                        tasks.get(i - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n"
                                + tasks.get(i - 1));
                    } else {
                        System.out.println("Index is invalid");
                    }
                }
            } else if (firstWord.equals("todo")) {
                if (remaining.equals("")) {
                    System.out.println("The description of a todo cannot be empty");
                } else {
                    Task t = new Todo(remaining);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n" + t
                            + "\nNow you have " + tasks.size() + " tasks in your list.");
                }
            } else if (firstWord.equals("deadline")) {
                if (remaining.equals("")) {
                    System.out.println("The description of a deadline cannot be empty");
                } else {
                    String[] desc_by = remaining.split(" */by *", 2);
                    String by = "";
                    if (desc_by.length == 2) {
                        by = desc_by[1];
                    }
                    Task t = new Deadline(desc_by[0], by);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n" + t
                            + "\nNow you have " + tasks.size() + " tasks in your list.");
                }
            } else if (firstWord.equals("event")) {
                if (remaining.equals("")) {
                    System.out.println("The description of an event cannot be empty");
                } else {
                    String[] desc_at = remaining.split(" */at *", 2);
                    String at = "";
                    if (desc_at.length == 2) {
                        at = desc_at[1];
                    }
                    Task t = new Event(desc_at[0], at);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n" + t
                            + "\nNow you have " + tasks.size() + " tasks in your list.");
                }
            } else {
                System.out.println("I'm sorry, but I don't know what that means :(");
            }
            System.out.println(frame + "\n");
        }
    }

}
