package duke;

import java.util.ArrayList;

/**
 * Duke is a bot that helps to keep track of tasks
 */
public class Duke {
    /**
     * Constructor for Duke
     */
    public Duke() {}

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        ArrayList<Task> listOfTask = storage.readFile();
        for (int i = 0; i < listOfTask.size(); i++) {
            TaskBank.getBank().add(listOfTask.get(i));
        }
        Ui.start();
        storage.writeToFile(TaskBank.getBank());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Parser parser = new Parser();
        String displayMessage = parser.parse(input);
        return displayMessage;
    }


}
