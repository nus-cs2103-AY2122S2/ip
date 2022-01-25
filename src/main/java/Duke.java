import java.util.Scanner;

import chatbot.DukeException;
import chatbot.Ui;
import chatbot.TaskList;
import chatbot.Storage;
import chatbot.Parser;

public class Duke {
    private Storage storage;
    private TaskList taskList;

    protected static String FILE_PATH = "./data/duke.txt";
    protected static String FILE_DIR = "./data";

    ////////////////////////////////////////////////////////////////
    // Main Methods

    public Duke(String filePath) {
        this.storage = new Storage(FILE_PATH, FILE_DIR);
        this.taskList = new TaskList();

        try { // attempt to load saved data
            storage.loadData(taskList);
        } catch (DukeException dukeException) {
            Ui.displayMessage(dukeException.toString());
        }
    }

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

    // introduction message
    public static void displayGreeting() {
        String introductionMessage = "Good day Sir. My name is Dook. \n How may I be of assistance?";
        Ui.displayMessage(introductionMessage);
    }
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        new Duke(FILE_PATH).run();
    }
}
