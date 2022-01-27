package bot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.time.LocalDate;

import java.util.ArrayList;
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

    private final ArrayList<Task> tasks = new ArrayList<>();

    public void start(InputStream inputStream, OutputStream outputStream) throws Exception {
        final Scanner input = new Scanner(inputStream);
        final PrintStream output = new PrintStream(outputStream);

        try {
            output.println(this.greet());
            String query = input.nextLine();
            while (!this.detectTerminationRequest(query)) {
                try {
                    output.println(this.process(query));
                } catch (BotException e) {
                    output.println(this.constructResponse(e.getMessage()));
                }
                query = input.nextLine();
            }
            output.println(this.bidFarewell());
        } finally {
            input.close();
            output.close();
        }
    }

    private String process(String query) throws BotException {
        final String[] tokens = query.split(" ", 2);
        switch (tokens[0]) {
            case "list":
                return this.getTaskList();
            case "mark":
                return this.markTask(Integer.parseInt(tokens[1]) - 1);
            case "unmark":
                return this.unmarkTask(Integer.parseInt(tokens[1]) - 1);
            case "todo":
                return this.addTask(new Todo(tokens[1].trim()));
            case "deadline":
                final String[] deadlineArgs = tokens[1].split(" /by ");
                return this.addTask(new Deadline(
                        deadlineArgs[0].trim(),
                        LocalDate.parse(deadlineArgs[1].trim(), Deadline.DATE_INPUT_FORMAT)));
            case "event":
                final String[] eventArgs = tokens[1].split(" /at ");
                return this.addTask(new Event(eventArgs[0].trim(), eventArgs[1].trim()));
            case "delete":
                return this.deleteTask(Integer.parseInt(tokens[1]) - 1);
            default:
                throw new BotException("I'm sorry, but I don't know what that means :-(");
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

    private String addTask(Task t) throws BotException {
        if (t.getDescription().equals("")) {
            throw new BotException("The description of a task cannot be empty.");
        }
        this.tasks.add(t);
        final String response =
                "Got it. I've added this task:\n  " + t + "\nNow you have " + this.tasks.size() + " tasks in the list.";
        return this.constructResponse(response);
    }

    private String markTask(int taskId) {
        final Task t = this.tasks.get(taskId);
        t.markAsDone();
        final String response = "Nice! I've marked this task as done:\n  " + t;
        return this.constructResponse(response);
    }

    private String unmarkTask(int taskId) {
        final Task t = this.tasks.get(taskId);
        t.markAsUndone();
        final String response = "OK, I've marked this task as not done yet:\n  " + t;
        return this.constructResponse(response);
    }

    private String deleteTask(int taskId) {
        final String response = "Noted. I've removed this task:\n  " + this.tasks.get(taskId) + "\nNow you have " +
                (this.tasks.size() - 1) + " tasks in the list.";
        this.tasks.remove(taskId);
        return this.constructResponse(response);
    }

    private String constructResponse(String content) {
        final String divider = this.INDENTATION + this.DIVIDER + "\n";
        final String response =
                this.INDENTATION + " " + content.replaceAll("\n", "\n " + this.INDENTATION) + "\n";
        return divider + response + divider;
    }

    private String constructTaskList(ArrayList<Task> tasks) {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                taskList += "\n";
            }
            taskList += (i + 1) + "." + tasks.get(i);
        }
        return taskList;
    }
}
