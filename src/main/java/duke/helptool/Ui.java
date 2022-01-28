package duke.helptool;

import java.util.Scanner;

/**
 * The type Ui.
 */
public class Ui {
    private static final Scanner myObj = new Scanner(System.in);

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }


    /**
     * Read command string.
     *
     * @return the string
     */
    public String readCommand() {
        return myObj.nextLine();
    }

    /**
     * Close reading.
     */
    public void closeReading() {
        myObj.close();
    }

    /**
     * Show exception error.
     *
     * @param e the e
     */
    public void showExceptionError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Show success mark.
     *
     * @param input the input
     */
    public void showSuccessMark(String input) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(input);
    }

    /**
     * Show success unmark.
     *
     * @param input the input
     */
    public void showSuccessUnmark(String input) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(input);
    }

    /**
     * Show delete.
     *
     * @param input           the input
     * @param sizeAfterDelete the size after delete
     */
    public void showDelete(String input, int sizeAfterDelete) {
        System.out.println("OK, I've removed this task:");
        System.out.println("\t" + input);
        System.out.format("Now you have %d tasks in the list.\n", sizeAfterDelete);
    }

    /**
     * Show list.
     *
     * @param tasks the tasks
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i) != null) {
                System.out.format("%s. %s\n", i + 1, tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Show matched task.
     *
     * @param tasks the tasks
     */
    public void showMatchedTask(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.format("%s. %s\n", i + 1, tasks.getTask(i).toString());
        }
    }

    /**
     * Show add todo.
     *
     * @param todo the todo
     * @param size the size
     */
    public void showAddTodo(String todo, int size) {
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                todo, size);
        System.out.println(result);
    }

    /**
     * Show add deadline.
     *
     * @param ddl  the ddl
     * @param size the size
     */
    public void showAddDeadline(String ddl, int size) {
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                ddl, size);
        System.out.println(result);
    }

    /**
     * Show add event.
     *
     * @param event the event
     * @param size  the size
     */
    public void showAddEvent(String event, int size) {
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                event, size);
        System.out.println(result);
    }

    /**
     * Show line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greet.
     */
    public void greet() {
        showLine();
        System.out.println("\tHello I am DDX");
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    /**
     * Bye.
     */
    public void bye() {
        // print result
        System.out.println("\tBye. Hope to see you again soon!");
    }

}
