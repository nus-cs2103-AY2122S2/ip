package chatbot;

import chatbot.exception.ChatBotException;
import chatbot.util.Parser;
import chatbot.util.Storage;
import chatbot.util.TaskList;
import chatbot.util.Ui;


/**
 * Represents a ChatBot AI (taking on the persona of a cheerful innkeeper)
 * that users (who are personified as passing travellers) can interact with to manage their schedule.
 */
public class ChatBot {

    private static final String SAVE_FILE_DIRECTORY = "data";
    private static final String SAVE_FILE_NAME = "data.txt";

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Instantiates a new ChatBot.
     */
    public ChatBot() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(SAVE_FILE_DIRECTORY, SAVE_FILE_NAME);
        try {
            storage.loadData(this.taskList);
        } catch (ChatBotException e) {
            e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        System.out.println("getting response!");
        Parser parser = new Parser(ui, storage, taskList);
        return parser.parse(input);
    }

    public String getGreeting() {
        return ui.greet(taskList.isEmpty());
    }
}
