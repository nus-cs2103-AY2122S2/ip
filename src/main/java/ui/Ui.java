package ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import tasks.Task;

/**
 * Represents a text interface for the chatbot.
 */
public class Ui {
    private static final String INDENTATION = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE_PREFIX = "☹ OOPS!!! ";

    private final Scanner input;
    private final PrintStream output;
    private final boolean isFrameEnabled;

    /**
     * Returns a UI object that can output a relevant text interface
     * for every user operation.
     *
     * @param inputStream is the input source to obtain user commands.
     * @param outputStream is the output source to write text responses.
     * @param isFrameEnabled determines whether a text response is boxed within a frame.
     * @throws IOException If the input or output stream fails to initialise.
     */
    public Ui(InputStream inputStream, OutputStream outputStream, boolean isFrameEnabled) throws IOException {
        this.input = new Scanner(inputStream);
        this.output = new PrintStream(outputStream);
        this.isFrameEnabled = isFrameEnabled;
    }

    /**
     * Accepts a user's input via the input source.
     *
     * @return The user's input to the chatbot.
     * @throws IOException If a failure occurred when reading a user's input
     * from the input source.
     */
    public String readCommand() throws IOException {
        return input.nextLine();
    }

    /**
     * Greets the user through text written to the output source.
     */
    public void showWelcome() {
        this.output.println(this.constructWelcomeResponse());
    }

    private String constructWelcomeResponse() {
        return this.constructResponse(Ui.WELCOME_MESSAGE);
    }

    /**
     * Bids farewell to a user through text written to the output source.
     */
    public void showExit() {
        this.output.println(this.constructExitResponse());
    }

    private String constructExitResponse() {
        return this.constructResponse(Ui.EXIT_MESSAGE);
    }

    /**
     * Format the given tasks as a list and display it to a user via the output source.
     *
     * @param tasks the tasks to be conveyed to a user.
     */
    public void showList(Task[] tasks) {
        this.output.println(this.constructListResponse(tasks));
    }

    private String constructListResponse(Task[] tasks) {
        final String message = "Here are the tasks in your list:\n"
                + this.constructTaskList(tasks);
        return this.constructResponse(message);
    }

    /**
     * Format the tasks that were found as a list and display it to a user via the
     * output source.
     *
     * @param tasks the tasks that are found from the keyword search.
     */
    public void showFindTasks(Task[] tasks) {
        this.output.println(this.constructFindResponse(tasks));
    }

    private String constructFindResponse(Task[] tasks) {
        final String message = "Here are the matching tasks in your list:\n"
                + this.constructTaskList(tasks);
        return this.constructResponse(message);
    }

    /**
     * Provides a response to the output source after a user has successfully added
     * a new task.
     *
     * @param task the task that the user has added.
     * @param totalTasksAfterAdd the total number of tasks left after the addition.
     */
    public void showAddTask(Task task, int totalTasksAfterAdd) {
        this.output.println(this.constructAddTaskResponse(task, totalTasksAfterAdd));
    }

    private String constructAddTaskResponse(Task task, int totalTasksAfterAdd) {
        final String message = "Got it. I've added this task:\n  " + task
                + "\nNow you have " + totalTasksAfterAdd + " tasks in the list.";
        return this.constructResponse(message);
    }

    /**
     * Provides a response to the output source after a user has successfully marked
     * a task as completed.
     *
     * @param task the task that the user has marked as completed.
     */
    public void showMarkTask(Task task) {
        this.output.println(this.constructMarkTaskResponse(task));
    }

    private String constructMarkTaskResponse(Task task) {
        final String message = "Nice! I've marked this task as done:\n  " + task;
        return this.constructResponse(message);
    }

    /**
     * Provides a response to the output source after a user has successfully marked
     * a task as uncompleted.
     *
     * @param task the task that the user has marked as uncompleted.
     */
    public void showUnmarkTask(Task task) {
        this.output.println(this.constructUnmarkTaskResponse(task));
    }

    private String constructUnmarkTaskResponse(Task task) {
        final String message = "OK, I've marked this task as not done yet:\n  " + task;
        return this.constructResponse(message);
    }

    /**
     * Provides a response to the output source after a user has successfully deleted
     * a task.
     *
     * @param task the task that the user has deleted.
     * @param totalTasksAfterDelete the total number of tasks left after the deletion.
     */
    public void showDeleteTask(Task task, int totalTasksAfterDelete) {
        this.output.println(this.constructDeleteTaskResponse(task, totalTasksAfterDelete));
    }

    private String constructDeleteTaskResponse(Task task, int totalTasksAfterDelete) {
        final String response = "Noted. I've removed this task:\n  " + task
                + "\nNow you have " + totalTasksAfterDelete + " tasks in the list.";
        return this.constructResponse(response);
    }

    /**
     * Provides a response to the output source after a user has successfully changed
     * the priority of a task.
     *
     * @param task the task to change the priority for.
     */
    public void showUpdatePriority(Task task) {
        this.output.println(this.constructUpdatePriorityResponse(task));
    }

    private String constructUpdatePriorityResponse(Task task) {
        final String message = "OK, I've changed the priority of this task:\n  " + task;
        return this.constructResponse(message);
    }

    /**
     * Writes a given error message to the output source.
     *
     * @param message the description of an error to be conveyed to the user.
     */
    public void showError(String message) {
        this.output.println(this.constructErrorResponse(message));
    }

    private String constructErrorResponse(String message) {
        return this.constructResponse(Ui.ERROR_MESSAGE_PREFIX + message);
    }

    private String constructResponse(String content) {
        final String indentedContent = content.replaceAll("\n", "\n " + Ui.INDENTATION);
        final String response = Ui.INDENTATION + " " + indentedContent + "\n";

        if (!this.isFrameEnabled) {
            return response;
        }

        final String indentedDivider = Ui.INDENTATION + Ui.DIVIDER + "\n";
        return indentedDivider + response + indentedDivider;
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
