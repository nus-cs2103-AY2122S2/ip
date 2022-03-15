package chatbot.command;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command which indicates to the user that their input was invalid
 */
public class InvalidCommand extends Command {

    /**
     * Instantiates a new Invalid command.
     */
    public InvalidCommand() {
        super(new String[0]);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        return ui.error(new ChatBotException().getMessage());
    }
}
