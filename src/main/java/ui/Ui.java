package ui;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import java.util.Scanner;

/**
 * Interface that the user interacts with.
 * Involves a scanner to read user inputs.
 */
public class Ui {

    TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts conversation with user by combining Parser and Storage.
     * @param parser Instance of Parser to process user commands.
     * @param storage Instance of storage to update data in storage
     * @throws DukeException If invalid input message is entered.
     */
    public void startConversation(Parser parser, Storage storage) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String[] inputStringArray = inputString.split(" ");

        while (!inputStringArray[0].equals("bye")) {
            parser.userCommand(inputStringArray, storage);
            inputString = sc.nextLine();
            inputStringArray = inputString.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon! :))");
    }

    /**
     * Displays appropriate error messages.
     * @param e DukeException for the error caused by incorrect user input.
     */
    public void showIllegalTextError(DukeException e) {
        System.out.println(e.toString());
    }

}
