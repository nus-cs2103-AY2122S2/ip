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
                if (commandSplitBySpace.length > 1) {
                    if (tasks.size() != 0 && Integer.parseInt(commandSplitBySpace[1]) - 1 <= tasks.size()) {
                        Task t = tasks.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                        t.markAsDone();
                        printMsg("Good job! I've marked this task as done:\n" + t);
                    } else {
                        printMsg("☹ OOPS!!! The task to be marked does not exist.");
                    }
                } else {
                    printMsg("☹ OOPS!!! The task to be marked has to be indicated.");
                }
            } else if (commandSplitBySpace[0].equals("unmark")) {
                if (commandSplitBySpace.length > 1) {
                    if (tasks.size() != 0 && Integer.parseInt(commandSplitBySpace[1]) - 1 <= tasks.size()) {
                        Task t = tasks.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                        t.markAsNotDone();
                        printMsg("Okay, I've marked this task as not done yet:\n" + t);
                    } else {
                        printMsg("☹ OOPS!!! The task to be unmarked does not exist.");
                    }
                } else {
                    printMsg("☹ OOPS!!! The task to be unmarked has to be indicated.");
                }
            } else if (commandSplitBySpace[0].equals("todo")) {
                if (commandSplitBySpace.length > 1) {
                    Todo t = new Todo(command.substring(5));
                    addTask(tasks, t);
                } else {
                    printMsg("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (commandSplitBySpace[0].equals("deadline")) {
                if (commandSplitBySpace.length > 1) {
                    int indexOfBy = command.indexOf("/by");
                    if (indexOfBy != -1) {
                        String desc = command.substring(9, indexOfBy - 1);
                        String by = command.substring(indexOfBy + 4);
                        Deadline d = new Deadline(desc, by);
                        addTask(tasks, d);
                    } else {
                        printMsg("☹ OOPS!!! The by of a deadline cannot be empty.");
                    }
                } else {
                    printMsg("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (commandSplitBySpace[0].equals("event")) {
                if (commandSplitBySpace.length > 1) {
                    int indexOfAt = command.indexOf("/at");
                    if (indexOfAt != -1) {
                        String desc = command.substring(6, indexOfAt - 1);
                        String at = command.substring(indexOfAt + 4);
                        Event e = new Event(desc, at);
                        addTask(tasks, e);
                    } else {
                        printMsg("☹ OOPS!!! The at of a deadline cannot be empty.");
                    }
                } else {
                    printMsg("☹ OOPS!!! The description of a event cannot be empty.");
                }

            } else {
                printMsg("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    /**
     * Adds new Task into ArrayList of Task and prints the message for adding this Task
     * @param tasks ArrayList of Task to contain the list of tasks added so far
     * @param t New Task to be added
     */
    public static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
        printMsg("Got it. I've added this task:\n  " + t + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
