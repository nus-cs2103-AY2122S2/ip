package chatbot.command;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command to delete a task from the user's task list.
 */
public class DeleteCommand extends Command {
    /**
     * Instantiates a new Delete command.
     *
     * @param input The input keyed in by the user.
     */
    public DeleteCommand(String[] input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        String[] input = getInput();
        String response;
        try {
            String type = input[1];
            if (input.length > 3) {
                throw new ChatBotException(
                        "That's too many inputs traveller! You only need to key in the index of the"
                                + String.format("%s you wish to delete!", type)
                );
            }
            if (type.equals("task")) {
                response = ui.createTaskResponse(
                        taskList.delete(Integer.parseInt(input[2]) - 1),
                        taskList
                );
                tasksStorage.saveChanges(taskList);
            } else if (type.equals("contact")) {
                response = ui.createContactResponse(
                        contactList.delete(Integer.parseInt(input[2]) - 1),
                        contactList
                );
                contactsStorage.saveChanges(contactList);
            } else {
                throw new ChatBotException("You can only delete a task or contact traveller!");
            }
            return response;
        } catch (ChatBotException e) {
            return ui.error(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.error(
                    "You should delete items using their index traveller!"
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.error(
                    "You need to specify which list to delete from and the index of the item traveller!"
            );
        }
    }
}
