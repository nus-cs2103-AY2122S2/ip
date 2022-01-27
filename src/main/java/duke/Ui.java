package duke;

import java.util.Scanner;

public class Ui {

    Ui() {}

    /**
     * Reads a line of input.
     * @return the input line
     */
    public static String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Displays a frame line.
     */
    public void showLine() {
        System.out.println("____________________________________________");
    }

    /**
     * Displays the list of tasks.
     * @param tasks the list of tasks, not null
     */
    public void showList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Display the task at the specified index in the list of tasks.
     * @param tasks the list of tasks, not null
     * @param index the index of the task to display
     */
    public void showListItem(TaskList tasks, int index) {
        System.out.println((index + 1) + ". " + tasks.get(index));
    }

    /**
     * Displays a message.
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

}
