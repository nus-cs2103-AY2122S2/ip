package duke.ui;

import java.util.Arrays;
import java.util.ArrayList;
import duke.task.Task;

/**
 * Handles all user-interface components of duke
 *
 */
public class Ui {
    /**
     * Outputs the specified strings entered in the following format:
     * ========================================== <br>
     * args[0] <br>
     * args[1] <br>
     * args[2] <br>
     * ========================================== <br>
     * <br>
     * For example, print("1st line", "2nd line", "3rd line" outputs <br>
     * ========================================== <br>
     * 1st line <br>
     * 2nd line <br>
     * 3rd line <br>
     * ==========================================
     * @param args strings to be displayed
     */
    public void print(String ... args) {
        System.out.printf("    %s%n", "==========================================");
        Arrays.asList(args).forEach((x) -> System.out.printf("    %s%n", x));
        System.out.printf("    %s%n", "==========================================");
    }

    /**
     * Outputs the specified list of task in the following format:
     * ==========================================<br>
     * Task 1 <br>
     * Task 2 <br>
     * Task 3 <br>
     * ==========================================
     * @param arr list of task to be displayed
     */
    public void print(ArrayList<Task> arr) {
        System.out.printf("    %s%n", "==========================================");
        if(arr.size() == 0)
            System.out.println("    Nothing to show!");
        else
            arr.forEach((x) -> System.out.printf("    %d. %s%n", arr.indexOf(x) + 1, x.toString()));
        System.out.printf("    %s%n", "==========================================");
    }

    public void print(ArrayList<Task> arr, String header) {
        System.out.println(String.format("    %s", "=========================================="));
        System.out.println(String.format("    %s", header));
        if(arr.size() == 0)
            System.out.println("    Nothing to show!");
        else
            arr.forEach((x) -> { System.out.println(String.format("    %d. %s", arr.indexOf(x) + 1, x.toString())); } );
        System.out.println(String.format("    %s", "=========================================="));
    }
}