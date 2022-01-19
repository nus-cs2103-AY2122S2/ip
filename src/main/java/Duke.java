import java.util.Scanner;
import tasks.Task;

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
                final String[] tokens = query.split(" ");
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
                    default:
                        Duke.handleAdd(new Task(query));
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
        final String response = Duke.constructResponse(taskList);
        System.out.println(response);
    }

    private static void handleAdd(Task t) {
        Duke.tasks[Duke.nextItemIndex++] = t;
        System.out.println(Duke.constructResponse("added: " + t));
    }

    private static void handleMark(int taskId) {
        final Task t = Duke.tasks[taskId];
        t.markAsDone();
        final String response = "Nice! I've marked this task as done:\n  [" + t.getStatusIcon() + "] " + t;
        System.out.println(Duke.constructResponse(response));
    }

    private static void handleUnmark(int taskId) {
        final Task t = Duke.tasks[taskId];
        t.markAsUndone();
        final String response = "OK, I've marked this task as not done yet:\n  [" + t.getStatusIcon() + "] " + t;
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
            taskList += currentTaskListItemIndex + ".[" + task.getStatusIcon() + "] " + task;
            currentTaskListItemIndex++;
        }

        return taskList;
    }
}
