package duke.main;

import java.io.IOException;

import duke.commands.Command;
import duke.common.DukeException;
import duke.constants.Constants;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Acts as a task manager that keeps tracks of all your tasks.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Creates a Duke object that initializes all the necessary components for the task manager program.
     * @param filePath filePath is the relative path to the text file that stores user's tasks.
     */
    public Duke(String filePath) {
        this.taskList = new TaskList();
        this.storage = new Storage(Constants.FILE_PATH + Constants.FILE_NAME);

        try {
            this.taskList = new TaskList(storage.loadFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";

        try {
            Parser parser = new Parser(input);
            Command c = parser.parse();

            response = c.execute(taskList, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        return response;
    }

//    Label userText = new Label(userInput.getText());
//        userText.setFont(new Font("Roboto", 12.5));
//        userText.setStyle("-fx-font-weight: bold");
//
//    Label abbyText = new Label(userInput.getText());
//        abbyText.setFont(new Font("Roboto", 12.5));
//        abbyText.setStyle("-fx-font-weight: bold");
}
