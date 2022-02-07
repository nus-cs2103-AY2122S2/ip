package duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke Chat Bot that allows users to track tasks.
 *
 * @author Benjamin Koh
 */

public class Duke {

    private static TaskList taskList;

    /**
     * Main method. Takes in user input and updates list, interacts with user
     *
     * @param args The user inputs
     */

    public static void main(String[] args) {
        Ui.welcome();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        taskList = new TaskList();

        try {
            Storage.initialise();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Storage.loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!input.equals("bye")) {
            try {
                Parser.parse(input, taskList);
            } catch (DukeException | IOException e) {
                System.out.println(e);
            } finally {
                input = scanner.nextLine();
            }
        }

        scanner.close();
        Ui.exit();
    }
}
