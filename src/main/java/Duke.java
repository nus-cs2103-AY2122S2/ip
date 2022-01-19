import java.util.Scanner;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final Task[] tasks = new Task[100];
    private static int nextItemIndex = 0;

    public static void main(String[] args) throws Exception {
        final Scanner input = new Scanner(System.in);
        Duke.greet();

        try {
            while (true) {
                final String query = input.nextLine();
                final String[] tokens = query.split(" ", 2);
                switch (tokens[0]) {
                    case "bye":
                        Duke.bidFarewell();
                        return;
                    case "list":
                        Duke.handleList();
                        break;
                    case "mark":
                        Duke.handleMark(Integer.parseInt(tokens[1]) - 1);
                        break;
                    case "unmark":
                        Duke.handleUnmark(Integer.parseInt(tokens[1]) - 1);
                        break;
                    case "todo":
                        Duke.handleAdd(new ToDo(tokens[1]));
                        break;
                    case "deadline":
                        final String[] deadlineArgs = tokens[1].split(" /by ");
                        Duke.handleAdd(new Deadline(deadlineArgs[0], deadlineArgs[1]));
                        break;
                    case "event":
                        final String[] eventArgs = tokens[1].split(" /at ");
                        Duke.handleAdd(new Event(eventArgs[0], eventArgs[1]));
                        break;
                }
            }
        } finally {
            input.close();
        }
    }

    private static void greet() {
        System.out.println(Duke.constructResponse(Duke.GREETING));
    }

    private static void bidFarewell() {
        System.out.println(Duke.constructResponse(Duke.FAREWELL));
    }

    private static void handleList() {
        final String taskList = "Here are the tasks in your list:\n" + Duke.constructTaskList(Duke.tasks);
        System.out.println(Duke.constructResponse(taskList));
    }

    private static void handleAdd(Task t) {
        Duke.tasks[Duke.nextItemIndex++] = t;
        final String response =
                "Got it. I've added this task:\n  " + t + "\nNow you have " + (Duke.nextItemIndex + 1)
                        + " tasks in the list.";
        System.out.println(Duke.constructResponse(response));
    }

    private static void handleMark(int taskId) {
        final Task t = Duke.tasks[taskId];
        t.markAsDone();
        final String response = "Nice! I've marked this task as done:\n  " + t;
        System.out.println(Duke.constructResponse(response));
    }

    private static void handleUnmark(int taskId) {
        final Task t = Duke.tasks[taskId];
        t.markAsUndone();
        final String response = "OK, I've marked this task as not done yet:\n  " + t;
        System.out.println(Duke.constructResponse(response));
    }

    private static String constructResponse(String content) {
        final String divider = Duke.INDENTATION + Duke.DIVIDER + "\n";
        final String response =
                Duke.INDENTATION + " " + content.replaceAll("\n", "\n " + Duke.INDENTATION) + "\n";
        return divider + response + divider;
    }

    private static String constructTaskList(Task[] tasks) {
        String taskList = "";
        int currentTaskListItemIndex = 1;

        for (int i = 0; i < tasks.length; i++) {
            final Task task = tasks[i];
            if (task == null) continue;
            if (currentTaskListItemIndex > 1) taskList += "\n";
            taskList += currentTaskListItemIndex + "." + task;
            currentTaskListItemIndex++;
        }

        return taskList;
    }
}
