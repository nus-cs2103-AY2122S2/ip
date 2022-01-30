package duke.ui;

import java.util.ArrayList;
import java.util.Arrays;

import duke.task.Task;

/**
 * Handles all user-interface components of duke.
 *
 */
public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Outputs the specified strings in the following format:
     * ========================================== <br>
     * args[0] <br>
     * args[1] <br>
     * args[2] <br>
     * ========================================== <br>
     * <br>
     * For example, print("1st line", "2nd line", "3rd line" outputs: <br>
     * ========================================== <br>
     * 1st line <br>
     * 2nd line <br>
     * 3rd line <br>
     * ==========================================
     * @param args strings to be displayed.
     */
    public void print(String ... args) {
        System.out.printf("    %s%n", "==========================================");
        Arrays.asList(args).forEach((x) -> System.out.printf("    %s%n", x));
        System.out.printf("    %s%n", "==========================================");
    }

    /**
     * Outputs the specified ArrayList of task in the following format:
     * ==========================================<br>
     * Task 1 <br>
     * Task 2 <br>
     * Task 3 <br>
     * ==========================================
     * @param arr list of task to be displayed.
     */
    public void print(ArrayList<Task> arr) {
        System.out.printf("    %s%n", "==========================================");
        if (arr.size() == 0) {
            System.out.println("    Nothing to show!");
        } else {
            arr.forEach((x) -> System.out.printf("    %d. %s%n", arr.indexOf(x) + 1, x.toString()));
        }
        System.out.printf("    %s%n", "==========================================");
    }

    /**
     * Outputs the specified ArrayList of task together with the specified in the following format:
     * ==========================================<br>
     * HEADER
     * Task 1 <br>
     * Task 2 <br>
     * Task 3 <br>
     * ==========================================
     * @param arr list of task to be displayed.
     */
    public void print(ArrayList<Task> arr, String header) {
        System.out.println(String.format("    %s", "=========================================="));
        System.out.println(String.format("    %s", header));
        if (arr.size() == 0) {
            System.out.println("    Nothing to show!");
        } else {
            arr.forEach((x) -> {
                System.out.println(String.format("    %d. %s", arr.indexOf(x) + 1, x.toString()));
            });
        }
        System.out.println(String.format("    %s", "=========================================="));
    }

    /**
     * Outputs the Duke logo.
     */
    public void printLogo() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke, What can I do for you?");
        System.out.println("-------------------------------------------\n");
    }
}
