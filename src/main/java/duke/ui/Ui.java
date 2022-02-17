package duke.ui;

import java.util.ArrayList;
import java.util.Arrays;

import duke.DialogBox;
import duke.task.Task;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Handles all user-interface components of duke.
 *
 */
public class Ui {
    private static VBox vbox;
    private static Image image;
    private String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Initializes Ui to output to the specfied Vbox, with the specfied image.
     * @param v The VBox to be displayed on
     * @param d The image for duke
     */
    public static void init(VBox v, Image d) {
        vbox = v;
        image = d;
    }

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
    public static void print(String ... args) {
        StringBuilder sb = new StringBuilder();
        Arrays.asList(args).forEach((x) -> sb.append(String.format("    %s\n", x)));
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
    public static void print(ArrayList<Task> arr) {
        StringBuilder sb = new StringBuilder();
        if (arr.size() == 0) {
            sb.append("    Nothing to show!");
        } else {
            arr.forEach((x) -> sb.append(String.format("    %d. %s%n", arr.indexOf(x) + 1, x.toString())));
        }
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
    public static void print(ArrayList<Task> arr, String header) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("    %s\n", header));
        if (arr.size() == 0) {
            sb.append("    Nothing to show!");
        } else {
            arr.forEach((x) -> sb.append(String.format("    %d. %s\n", arr.indexOf(x) + 1, x.toString())));
        }
        print(sb.toString());
    }

    /**
     * Prints the specified string onto the GUI.
     * @param ss The string that is to be displayed
     */
    public static void print(String ss) {
        vbox.getChildren().addAll(
                DialogBox.getDukeDialog(ss, image)
        );
    }

}
