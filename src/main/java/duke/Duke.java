package duke;

import duke.exception.DukeException;
import duke.misc.Parser;
import duke.misc.Storage;
import duke.misc.TaskList;
import duke.misc.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * KoroBot is a chatbot that tracks the list of tasks on hand.
 * @author Terng Yan Long
 * @version 9.0
 * @since 1.0
 */
public class Duke {
    public static void main(String[] args) {
        Ui.greet();

        try {
            Storage.initFileFolder();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            Storage.readData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Ui.welcome();
        TaskList taskListOfTasks = Storage.initTaskList(100); // Assume there will be no more than 100 tasks

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            try {
                Parser.parse(userInput, taskListOfTasks);
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                userInput = sc.nextLine();
            }
        }
        sc.close();
        Ui.exit();
    }
}
