package chatbot.command;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command to add a Deadline or Event to the user's task list.
 */
public class AddNonTodoTaskCommand extends Command {

    /**
     * Instantiates a new Add task command.
     *
     * @param rawInput The input keyed in by the user.
     */
    public AddNonTodoTaskCommand(String rawInput) {
        super(rawInput.split("/", 2));
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        String[] input = getInput();
        String response;
        try {
            if (input.length == 1) {
                handleErrorMessages(input);
            }
            response = ui.createTaskResponse(
                    taskList.add(
                            input[0].split(" "),
                            input[1].split(" ")
                    ), taskList
            );
            tasksStorage.saveChanges(taskList);
            return response;
        } catch (ChatBotException e) {
            return ui.error(e.getMessage());
        }
    }

    private void handleErrorMessages(String[] input) throws ChatBotException {
        String[] splitInput = input[0].split(" ");
        String type = splitInput[0];
        String slashMessage;
        String inputsMessage;
        String errorMessage;

        if (type.equals("deadline")) {
            slashMessage = "/by in your command to add a deadline traveller!";
            inputsMessage = "due date and time of your deadline traveller!";
        } else if (type.equals("event")) {
            slashMessage = "/at in your command to add a deadline traveller!";
            inputsMessage = "timestamp of your event traveller!";
        } else {
            throw new ChatBotException();
        }

        if (splitInput.length == 1) {
            errorMessage = "You need to key in the " + inputsMessage;
        } else {
            errorMessage = "You need to include " + slashMessage;
        }
        throw new ChatBotException(errorMessage);
    }
}
