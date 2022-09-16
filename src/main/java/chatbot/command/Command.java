package chatbot.command;

import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * The type Command.
 */
public abstract class Command {
    private String[] input;

    /**
     * Instantiates a new Command.
     *
     * @param input The input keyed in by the user.
     */
    public Command(String[] input) {
        this.input = input;
    }

    /**
     * Gets the input keyed in by the user.
     *
     * @return The input keyed in by the user.
     */
    public String[] getInput() {
        return input;
    }

    /**
     * Execute string.
     *
     * @param taskList        The user's task list.
     * @param contactList     The user's contact list.
     * @param tasksStorage    The storage handler for the user's task list.
     * @param contactsStorage The storage handler for the user's contact list.
     * @param ui              The Innkeeper UI.
     * @return The response from Innkeeper after executing the command
     */
    public abstract String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                                   Storage contactsStorage, Ui ui);
}
