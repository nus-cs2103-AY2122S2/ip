package chatbot;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Parser;
import chatbot.util.Storage;
import chatbot.util.Ui;


/**
 * Represents a ChatBot AI (taking on the persona of a cheerful innkeeper)
 * that users (who are personified as passing travellers) can interact with to manage their schedule.
 */
public class ChatBot {

    private static final String TASKS_SAVE_FILE_DIRECTORY = "tasks";
    private static final String TASKS_SAVE_FILE_NAME = "data.txt";
    private static final String CONTACTS_SAVE_FILE_DIRECTORY = "contacts";
    private static final String CONTACTS_SAVE_FILE_NAME = "data.txt";

    private final Ui ui;
    private final TaskList taskList;
    private final ContactList contactList;
    private final Storage tasksStorage;
    private final Storage contactsStorage;

    /**
     * Instantiates a new ChatBot.
     */
    public ChatBot() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.contactList = new ContactList();
        this.tasksStorage = new Storage(TASKS_SAVE_FILE_DIRECTORY, TASKS_SAVE_FILE_NAME);
        this.contactsStorage = new Storage(CONTACTS_SAVE_FILE_DIRECTORY, CONTACTS_SAVE_FILE_NAME);
        try {
            tasksStorage.loadData(this.taskList);
            contactsStorage.loadData(this.contactList);
        } catch (ChatBotException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a response from the ChatBot to the user's input.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(ui, tasksStorage, contactsStorage, taskList, contactList);
        return parser.parse(input);
    }

    /**
     * Gets greeting message when ChatBot is first started up.
     *
     * @return The greeting message.
     */
    public String getGreeting() {
        return ui.greet(taskList.isEmpty() && contactList.isEmpty());
    }
}
