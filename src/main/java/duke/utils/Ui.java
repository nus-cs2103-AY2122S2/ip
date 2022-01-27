package duke.utils;

import java.util.Scanner;

import duke.task.Task;

/**
 * The type Ui.
 */
public class Ui {
    /**
     * Show welcome message.
     */
    public void showWelcome() {
        String logo = "\n"
                + "   ____                  _                           \n"
                + "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n"
                + " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n"
                + " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n"
                + "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n"
                + "                                                     \n";
        System.out.println("Hello from\n" + logo + "\nMy name is Cortana, what can I do for you?");
    }

    /**
     * Show separator line.
     */
    public void showLine() {
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
    }

    /**
     * Read user input from input stream.
     *
     * @return the user input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();
        return input;
    }

    /**
     * Show error message.
     *
     * @param errorMessage the error message
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * No task left Ui.
     */
    public void noTaskLeft() {
        System.out.println("You are done for the day, or are you?");
    }

    /**
     * Print task Ui.
     *
     * @param task the task to print
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Show added task and Ui.
     *
     * @param tasks the TaskList tasks
     * @param task  the task
     */
    public void addedTask(TaskList tasks, Task task) {
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "duke/task" : "tasks";
        System.out.println("Got it. I've added this task: \n" + " " + task
                + "\nNow you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.");
    }

    /**
     * Show deleted task and Ui.
     *
     * @param tasks       the TaskList tasks
     * @param taskDeleted the task deleted
     */
    public void deletedTask(TaskList tasks, Task taskDeleted) {
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "duke/task" : "tasks";
        System.out.println("Noted. I've removed this task: \n" + " " + taskDeleted + "\n"
                + "Now you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.");
    }

    /**
     * Show deleted all task Ui.
     */
    public void deletedAll() {
        System.out.println("All tasks have been removed!");
    }

    /**
     * Show exited Ui.
     */
    public void exited() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Show listed task Ui.
     *
     * @param index the index of the task to list
     * @param task  the task
     */
    public void listed(int index, Task task) {
        System.out.println(index + "." + task);
    }

    /**
     * Show marked task Ui.
     *
     * @param task the task to be marked
     */
    public void marked(Task task) {
        System.out.println("Nice! I've marked this task as done: \n " + task);
    }

    /**
     * Show unmarked task Ui.
     *
     * @param task the task to be unmarked
     */
    public void unmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n " + task);
    }

    /**
     * Show founded task on the same date and Ui.
     *
     * @param numberOfTasksOnSameDate the number of tasks on the same date
     * @param time                    the date/time
     */
    public void foundTaskOnSameDate(int numberOfTasksOnSameDate, String time) {
        String taskOrTasks = numberOfTasksOnSameDate <= 1 ? "duke/task" : "tasks";
        System.out.printf("Found %d %s with date/time %s.\n", numberOfTasksOnSameDate, taskOrTasks, time);
    }

    /**
     * Show founded task with the same keyword.
     *
     * @param numberOfTasksMatchKeyword the number of tasks on the same date
     * @param keyword                   the keyword to search for
     */
    public void foundTasksMatchKeyword(int numberOfTasksMatchKeyword, String keyword) {
        String taskOrTasks = numberOfTasksMatchKeyword <= 1 ? "task" : "tasks";
        System.out.printf("Found %d %s containing keyword \"%s\".\n", numberOfTasksMatchKeyword, taskOrTasks, keyword);
    }
}
