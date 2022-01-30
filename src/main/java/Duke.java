import java.util.Scanner;

import chatbot.DukeException;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;

/**
* Duke class for the bot. Contains the main method.
*/
public class Duke {
    protected static final String FILEDIR = "./data";
    protected static final String FILEPATH = "./data/duke.txt";

    private Storage storage;
    private TaskList taskList;

    ////////////////////////////////////////////////////////////////
    // Main Methods

    /**
    * Class constructor.
    *
    * @param  filePath  a string that denotes the intended directory of the
    * stored data text file for the bot
    */
    public Duke(String filePath) {
        this.storage = new Storage(FILEPATH, FILEDIR);
        this.taskList = new TaskList();

        try { // attempt to load saved data
            storage.loadData(taskList);
        } catch (DukeException dukeException) {
            Ui.displayMessage(dukeException.toString());
        }
    }

    /**
    * Runs the Duke bot.
    */
    public void run() {
        // print introduction message
        displayGreeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String inputText = scanner.nextLine().trim();
            boolean parsedText = Parser.parseText(inputText, taskList, storage);
            if (!parsedText) { // bot should not parse anymore
                scanner.close();
                break;
            }
        }
    }

    /**
    * Displays the greeting message to the user.
    */
    public static void displayGreeting() {
        String introductionMessage = "Good day Sir. My name is Dook. \n How may I be of assistance?";
        Ui.displayMessage(introductionMessage);
    }

    /**
     * Main function that is run on startup of the bot.
     */
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        new Duke(FILEPATH).run();
    }
}
