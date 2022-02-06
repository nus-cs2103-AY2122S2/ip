package ui;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;

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
    public String startConversation(Parser parser, Storage storage, String userInput) throws DukeException {
        String[] inputStringArray = userInput.split(" ");
        return parser.userCommand(inputStringArray, storage);
    }

    /**
     * Displays appropriate error messages.
     * @param e DukeException for the error caused by incorrect user input.
     * @returns Error message.
     */
    public String showIllegalTextError(DukeException e) {
        return e.getMessage();
    }

}
