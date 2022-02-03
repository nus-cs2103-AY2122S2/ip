package DukeComponent;

import Exceptions.TaskException;
import java.util.Scanner;

/**
 * A class that belongs to the DukeComponent Package.
 * This class deals with interactions with the user.
 */
public class Ui {

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        initUi();
    }

    /**
     * Runs the Ui for the Duke program.
     * @param sc Scanner to allow continuous user input from the CLI.
     * @param tasks TaskList that is manipulated by the {@link DukeComponent.Command} class.
     * @param storage Storage that is manipulated by the {@link DukeComponent.Command} class.
     */
    public void run(Scanner sc, TaskList tasks, Storage storage) {
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            try {

                Parser p = new Parser(userInput);
                p.executeCommand(tasks);
                storage.write(tasks);
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
            printHorizontalLine();
            if (userInput.equals("bye")) break;
        }
    }

    /**
     * Initialise the Duke Ui.
     */
    private void initUi() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Private helper method to print the horizontal lines in Duke Ui.
     */
    private static void printHorizontalLine() {
        System.out.println("_____________" +
                "_______________________________________________");
    }
}
