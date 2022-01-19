import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "Hi! I'm Ruby, How can I help you?";
        printMsg(welcome);
        String command = "";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            command = sc.nextLine();
            String[] commandSplitBySpace = command.split(" ");
            if (commandSplitBySpace[0].equals("bye")) {
                break;
            } else if (commandSplitBySpace[0].equals("list")) {
                StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
                for (int i = 0; i < tasks.size(); i++) {
                    if (i > 0) {
                        sb.append("\n");
                    }
                    sb.append(Integer.toString(i + 1) + "." + tasks.get(i));
                }
                printMsg(sb.toString());
            } else if (commandSplitBySpace[0].equals("mark")) {
                Task t = tasks.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                t.markAsDone();
                printMsg("Good job! I've marked this task as done:\n" + t);
            } else if (commandSplitBySpace[0].equals("unmark")) {
                Task t = tasks.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                t.markAsNotDone();
                printMsg("Okay, I've marked this task as not done yet:\n" + t);
            } else if (commandSplitBySpace[0].equals("todo")) {
                Todo t = new Todo(command.substring(5));
                addTask(tasks, t);
            } else if (commandSplitBySpace[0].equals("deadline")) {
                int indexOfBy = command.indexOf("/by");
                String desc = command.substring(9, indexOfBy - 1);
                String by = command.substring(indexOfBy + 4);
                Deadline d = new Deadline(desc, by);
                addTask(tasks, d);
            } else if (commandSplitBySpace[0].equals("event")) {
                int indexOfAt = command.indexOf("/at");
                String desc = command.substring(6, indexOfAt - 1);
                String at = command.substring(indexOfAt + 4);
                Event e = new Event(desc, at);
                addTask(tasks, e);
            } else {
                tasks.add(new Task(command));
                printMsg("added: " + command);
            }
        }
        printMsg("Okay, bye! Hope to see you again :)");
    }

    /**
     * Prints the message that is parsed into this method with dividers.
     *
     * @param msg a String containing the message to be printed.
     */
    public static void printMsg(String msg) {
        String divider = "---------------------------------------------------------";
        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
    }

    public static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
        printMsg("Got it. I've added this task:\n  " + t + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
