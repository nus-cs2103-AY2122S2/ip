package duke.ui;

import java.util.ArrayList;
import java.util.Arrays;

import duke.DialogBox;
import duke.task.Task;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Handles all user-interface components of duke.
 *
 */
public class Ui {
    private VBox v;
    private Image d;

    public Ui(VBox v, Image d) {
        this.v = v;
        this.d = d;
    }
    private String logo =
              " ____        _        \n"
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
        StringBuilder sb = new StringBuilder();
//        System.out.printf("    %s%n", "==========================================");
//        Arrays.asList(args).forEach((x) -> System.out.printf("    %s%n", x));
//        System.out.printf("    %s%n", "==========================================");
        sb.append(String.format("    %s%n", "========================"));
        Arrays.asList(args).forEach((x) -> sb.append(String.format("    %s%n", x)));
        sb.append(String.format("    %s%n", "========================"));
        print(sb.toString());
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
        StringBuilder sb = new StringBuilder();
//        System.out.printf("    %s%n", "==========================================");
//        if (arr.size() == 0) {
//            System.out.println("    Nothing to show!");
//        } else {
//            arr.forEach((x) -> System.out.printf("    %d. %s%n", arr.indexOf(x) + 1, x.toString()));
//        }
//        System.out.printf("    %s%n", "==========================================");
        sb.append(String.format("    %s%n", "========================"));
        if (arr.size() == 0) {
            sb.append("    Nothing to show!");
        } else {
            arr.forEach((x) -> sb.append(String.format("    %d. %s%n", arr.indexOf(x) + 1, x.toString())));
        }
        sb.append(String.format("    %s%n", "========================"));
        print(sb.toString());
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
//        System.out.println(String.format("    %s", "=========================================="));
//        System.out.println(String.format("    %s", header));
//        if (arr.size() == 0) {
//            System.out.println("    Nothing to show!");
//        } else {
//            arr.forEach((x) -> {
//                System.out.println(String.format("    %d. %s", arr.indexOf(x) + 1, x.toString()));
//            });
//        }
//        System.out.println(String.format("    %s", "=========================================="));

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("    %s%n", "========================"));
        sb.append(String.format("    %s", header));
        if (arr.size() == 0) {
            sb.append("    Nothing to show!");
        } else {
            arr.forEach((x) -> sb.append(String.format("    %d. %s%n", arr.indexOf(x) + 1, x.toString())));
        }
        sb.append(String.format("    %s%n", "========================"));
        print(sb.toString());
    }

    public void print(String ss) {
        Label dukeText = new Label(ss);
        v.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(d))
        );
    }

    /**
     * Outputs the Duke logo.
     */
    public void printLogo(VBox v, String ss, Image d) {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke, What can I do for you?");
        System.out.println("-------------------------------------------\n");
    }

    public void printLogo() {
        print("hey hey hey hey");
    }
}
