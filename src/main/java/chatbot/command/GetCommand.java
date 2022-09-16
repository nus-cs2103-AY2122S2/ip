package chatbot.command;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command to find tasks in the user's task list which occur on the given date or timestamp.
 */
public class GetCommand extends Command {

    /**
     * Instantiates a new Get command.
     *
     * @param input The input keyed in by the user.
     */
    public GetCommand(String[] input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                                   Storage contactsStorage, Ui ui) {
        String[] input = getInput();
        try {
            if (input.length == 1) {
                return "You need to key in the "
                        + "date or timestamp for which you want to view your tasks!";
            } else if (input.length > 2) {
                return "That's too many inputs traveller! You only need to key in the "
                        + "date or timestamp for which you want to view your tasks!";
            }
            Timestamp date = new Timestamp(input[1]);
            return taskList.getTasksOnDate(date);
        } catch (
        ChatBotException e) {
            return ui.error(e.getMessage());
        }
    }
}
