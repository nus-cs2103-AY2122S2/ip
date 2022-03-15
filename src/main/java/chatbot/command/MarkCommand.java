package chatbot.command;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command that marks or unmarks tasks in the user's task list.
 */
public class MarkCommand extends Command {

    /**
     * Instantiates a new Mark command.
     *
     * @param input The input keyed in by the user.
     */
    public MarkCommand(String[] input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        String[] input = getInput();
        String response;
        try {
            if (taskList.isEmpty()) {
                return "Your task list is empty traveller! "
                        + "Add some tasks first before attempting to mark or unmark!";
            } else if (input.length > 2) {
                return "That's too many inputs traveller! "
                        + "You only need to key in the index of the task you wish to mark or unmark!";
            }
            response = markOrUnmark(
                    Integer.parseInt(input[1]) - 1,
                    input[0].equals("mark"),
                    taskList,
                    tasksStorage
            );
            return response;
        } catch (ChatBotException e) {
            return ui.error(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.error(
                    "You should mark and unmark tasks using their index rather than title traveller!"
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.error(
                    "You need to key in the index of the task you wish to mark or unmark traveller!"
            );
        }
    }

    private String markOrUnmark(int index, boolean mark, TaskList taskList, Storage tasksStorage)
            throws ChatBotException {
        if (taskList.isInvalidIndex(index)) {
            throw new ChatBotException(
                    "This is an invalid task index traveller! You can type list to check all task indexes!"
            );
        }
        String response;
        if (mark) {
            response = taskList.mark(index);
        } else {
            response = taskList.unmark(index);
        }
        tasksStorage.saveChanges(taskList);
        return response;
    }
}
