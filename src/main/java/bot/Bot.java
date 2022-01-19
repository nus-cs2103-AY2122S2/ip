package bot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Bot {
    private static final String INDENTATION = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    private final Task[] tasks = new Task[100];
    private int nextItemIndex = 0;

    public void start(InputStream inputStream, OutputStream outputStream) throws Exception {
        final Scanner input = new Scanner(inputStream);
        final PrintStream output = new PrintStream(outputStream);

        try {
            output.println(this.greet());
            String query = input.nextLine();
            while (!this.detectTerminationRequest(query)) {
                output.println(this.process(query));
                query = input.nextLine();
            }
            output.println(this.bidFarewell());
        } catch (Exception e) {
            throw e;
        } finally {
            input.close();
            output.close();
        }
    }

    private String process(String query) throws Exception {
        final String[] tokens = query.split(" ", 2);
        switch (tokens[0]) {
            case "list":
                return this.getTaskList();
            case "mark":
                return this.markTask(Integer.parseInt(tokens[1]) - 1);
            case "unmark":
                return this.unmarkTask(Integer.parseInt(tokens[1]) - 1);
            case "todo":
                return this.addTask(new Todo(tokens[1]));
            case "deadline":
                final String[] deadlineArgs = tokens[1].split(" /by ");
                return this.addTask(new Deadline(deadlineArgs[0], deadlineArgs[1]));
            case "event":
                final String[] eventArgs = tokens[1].split(" /at ");
                return this.addTask(new Event(eventArgs[0], eventArgs[1]));
            default:
                return "";
        }
    }

    private boolean detectTerminationRequest(String query) {
        return query.equals("bye");
    }

    private String greet() {
        return this.constructResponse(Bot.GREETING);
    }

    private String bidFarewell() {
        return this.constructResponse(Bot.FAREWELL);
    }

    private String getTaskList() {
        final String taskList = "Here are the tasks in your list:\n" + this.constructTaskList(this.tasks);
        return this.constructResponse(taskList);
    }

    private String addTask(Task t) {
        this.tasks[this.nextItemIndex++] = t;
        final String response =
                "Got it. I've added this task:\n  " + t + "\nNow you have " + this.nextItemIndex + " tasks in the list.";
        return this.constructResponse(response);
    }

    private String markTask(int taskId) {
        final Task t = this.tasks[taskId];
        t.markAsDone();
        final String response = "Nice! I've marked this task as done:\n  " + t;
        return this.constructResponse(response);
    }

    private String unmarkTask(int taskId) {
        final Task t = this.tasks[taskId];
        t.markAsUndone();
        final String response = "OK, I've marked this task as not done yet:\n  " + t;
        return this.constructResponse(response);
    }

    private String constructResponse(String content) {
        final String divider = this.INDENTATION + this.DIVIDER + "\n";
        final String response =
                this.INDENTATION + " " + content.replaceAll("\n", "\n " + this.INDENTATION) + "\n";
        return divider + response + divider;
    }

    private String constructTaskList(Task[] tasks) {
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
