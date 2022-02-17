package ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.Scanner;

import tasks.Task;

public class Ui {
    private static final String INDENTATION = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE_PREFIX = "☹ OOPS!!! ";

    private final Scanner input;
    private final PrintStream output;

    public Ui(InputStream inputStream, OutputStream outputStream) throws IOException {
        this.input = new Scanner(inputStream);;
        this.output = new PrintStream(outputStream);
    }

    public String readCommand() throws IOException {
        return input.nextLine();
    }

    public void showWelcome() {
        this.output.println(this.constructWelcomeResponse());
    }

    private String constructWelcomeResponse() {
        return this.constructResponse(Ui.WELCOME_MESSAGE);
    }

    public void showExit() {
        this.output.println(this.constructExitResponse());
    }

    private String constructExitResponse() {
        return this.constructResponse(Ui.EXIT_MESSAGE);
    }

    public void showList(Task[] tasks) {
        this.output.println(this.constructListResponse(tasks));
    }

    private String constructListResponse(Task[] tasks) {
        final String message = "Here are the tasks in your list:\n"
                + this.constructTaskList(tasks);
        return this.constructResponse(message);
    }

    public void showFindTasks(Task[] tasks) {
        this.output.println(this.constructFindResponse(tasks));
    }

    private String constructFindResponse(Task[] tasks) {
        final String message = "Here are the matching tasks in your list:\n"
                + this.constructTaskList(tasks);
        return this.constructResponse(message);
    }

    public void showAddTask(Task task, int totalTasksAfterAdd) {
        this.output.println(this.constructAddTaskResponse(task, totalTasksAfterAdd));
    }

    private String constructAddTaskResponse(Task task, int totalTasksAfterAdd) {
        final String message = "Got it. I've added this task:\n  " + task
                + "\nNow you have " + totalTasksAfterAdd + " tasks in the list.";
        return this.constructResponse(message);
    }

    public void showMarkTask(Task task) {
        this.output.println(this.constructMarkTaskResponse(task));
    }

    private String constructMarkTaskResponse(Task task) {
        final String message = "Nice! I've marked this task as done:\n  " + task;
        return this.constructResponse(message);
    }

    public void showUnmarkTask(Task task) {
        this.output.println(this.constructUnmarkTaskResponse(task));
    }

    private String constructUnmarkTaskResponse(Task task) {
        final String message = "OK, I've marked this task as not done yet:\n  " + task;
        return this.constructResponse(message);
    }

    public void showDeleteTask(Task task, int totalTasksAfterDelete) {
        this.output.println(this.constructDeleteTaskResponse(task, totalTasksAfterDelete));
    }

    private String constructDeleteTaskResponse(Task task, int totalTasksAfterDelete) {
        final String response = "Noted. I've removed this task:\n  " + task
                + "\nNow you have " + totalTasksAfterDelete + " tasks in the list.";
        return this.constructResponse(response);
    }

    public void showError(String message) {
        this.output.println(this.constructErrorResponse(message));
    }

    private String constructErrorResponse(String message) {
        return this.constructResponse(Ui.ERROR_MESSAGE_PREFIX + message);
    }

    private String constructResponse(String content) {
        final String divider = this.INDENTATION + this.DIVIDER + "\n";
        final String response =
                this.INDENTATION + " " + content.replaceAll("\n", "\n " + this.INDENTATION) + "\n";
        return divider + response + divider;
    }

    private String constructTaskList(Task[] tasks) {
        String taskList = "";
        for (int i = 0; i < tasks.length; i++) {
            if (i > 0) {
                taskList += "\n";
            }
            taskList += (i + 1) + "." + tasks[i];
        }
        return taskList;
    }
}
