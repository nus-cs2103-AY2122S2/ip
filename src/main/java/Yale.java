import yale.command.Parser;
import yale.command.Storage;
import yale.command.Ui;
import yale.task.TaskList;

import javafx.scene.control.Label;

import java.util.Scanner;

/**
 * Represents a chatbot that tracks
 * tasks for users.
 */
public class Yale {
    /**
     * Creates Ui, Storage, Parser
     * Scanner and TaskList objects.
     */

    private static final String FILE_PATH = "data/yale.txt";
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList list;

    /**
     * Constructor method
     */
    public Yale() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();
        list = new TaskList();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Yale heard: " + input;
    }

    /**
     * Starts the chatbot program.
     */
    public void run() {
        ui.welcomePrompt();
        Scanner scanner = new Scanner(System.in);
        String fileData = storage.loadFileContents();
        list.importIn(fileData);

        while (true) {
            String command = ui.receiveInput(scanner);
            parser.performAction(command, list);
            storage.writeTextTo(list);
            if (ui.checkExit(command)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Yale().run();
    }
}

