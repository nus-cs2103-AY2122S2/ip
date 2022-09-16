package chatbot.command;

import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates a new Bye command.
     */
    public ByeCommand() {
        super(new String[0]);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        return ui.bye();
    }
}
