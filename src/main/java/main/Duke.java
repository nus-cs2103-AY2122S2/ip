package main;

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
    private boolean hasLoadedData = false;

    ////////////////////////////////////////////////////////////////
    // Main Methods

    /**
    * Class constructor.
    */
    public Duke() {
        this.storage = new Storage(FILEPATH, FILEDIR);
        this.taskList = new TaskList();

        try { // attempt to load saved data
            storage.loadData(taskList);
            hasLoadedData = true;
        } catch (DukeException dukeException) {
            Ui.displayMessage(dukeException.toString());
        }
    }

    /**
     * Returns the String containing the bot's reply to loading data
     *
     * @return          the reply of the bot of whether data was loaded
     */
    public String getLoadDataResponse() {
        if (hasLoadedData) {
            return storage.LOAD_SUCCESS;
        } else {
            return storage.UNREADABLE_FILE;
        }
    }

    /**
     * Returns the String containing the bot's reply to the String inputText
     *
     * @param inputText the String from the user's input
     * @return          the reply of the bot to the input
     */
    public String getResponse(String inputText) {
        return Parser.parseTextGui(inputText, taskList, storage);
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
        new Duke().run();
    }
}
