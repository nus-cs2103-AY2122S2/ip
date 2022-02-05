package duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static TaskList taskList;

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
