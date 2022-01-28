import data.Storage;
import data.TaskList;
import ui.ChatBot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main entry class to run ChatBot instance.
 */
class Duke {
    /** Default path for data file: data/duke.txt */
    private static final Path DEFAULT_FILE_PATH = Paths.get("data", "duke.txt");

    public static void main(String[] args) throws IOException {
        Storage store = Storage.initStorage(DEFAULT_FILE_PATH);
        TaskList taskList = TaskList.initTaskList(store);

        ChatBot chatBot = new ChatBot(taskList);
        chatBot.initialise();

        Scanner scanner = new Scanner(System.in);
        while (!chatBot.hasTerminated()) {
            String input = scanner.nextLine();
            chatBot.runCommand(input);
        }
    }
}
