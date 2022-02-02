package duke.ui;

import duke.data.Storage;
import duke.data.TaskList;
import duke.chatbot.ChatBot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Duke class serving as interface between
 * GUI and chatbot logic.
 */
public class Duke {
    /** Default path for data file: data/duke.txt */
    private static final Path DEFAULT_FILE_PATH = Paths.get("data", "duke.txt");

    /** ChatBot instance for running of user commands */
    private ChatBot chatBot = null;

    /**
     * Creates a duke instance initialised with default data file path.
     */
    public Duke() {
        try {
            Storage store = Storage.initStorage(DEFAULT_FILE_PATH);
            TaskList taskList = TaskList.initTaskList(store);
            this.chatBot = new ChatBot(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a boolean on whether the chatbot
     * has received a termination command.
     *
     * @return Whether the chatbot has terminated.
     */
    public boolean hasTerminated() {
        return this.chatBot.hasTerminated();
    }

    /**
     * Runs the user command based on the input string and gets
     * the feedback response from ChatBot.
     *
     * @param input User input command to run.
     * @return String feedback for command ran.
     */
    public String getResponse(String input) {
        ArrayList<String> response = chatBot.runCommand(input);
        // Show each item in response in a new line
        return response.stream().reduce("", (first, second) -> first + "\n" + second);
    }
}
