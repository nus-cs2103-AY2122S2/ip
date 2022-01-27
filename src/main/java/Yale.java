import yale.command.Parser;
import yale.command.Storage;
import yale.command.Ui;
import yale.task.TaskList;

import java.util.Scanner;

/**
 * Represents a chatbot that tracks
 * tasks for users.
 */
public class Yale {
    /**
     * Creates Ui, Storage, Parser
     * Scanner and TaskList objects
     * @param args
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.welcomePrompt();
        Storage storage = new Storage("data/yale.txt");
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
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
}

