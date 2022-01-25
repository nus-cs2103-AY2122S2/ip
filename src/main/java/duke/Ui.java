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
     * @param tasks the list of tasks
     */
    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list~");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a message.
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

}
