package duke;

import java.io.IOException;
import java.util.MissingFormatArgumentException;

/**
 * Represents the Duke class that serves to interpret and generate response for DukeLCH.
 */
public class Duke {

    private DukeContact contact;
    private DukeHistory history;
    private DukeStorage storage;
    private DukeUi ui;
    private String response;

    /**
     * Constructor for Duke class that takes in a filePath to load data from the users local hard disk.
     * @param filePath A String representing a given filePath.
     */
    public Duke(String filePath) {
        contact = new DukeContact();
        history = new DukeHistory();
        storage = new DukeStorage();
        ui = new DukeUi();

        String[] arr = filePath.split("\\\\");
        try {
            storage.startup(arr[0], arr[1]);
            storage.restore(history);
        } catch (IOException ex) {
            ui.printLoadError();
        }
    }

    /**
     * Method that updates the latest response to a user's command.
     * @return DukeLCH's response.
     */
    public String run(String input) {
        DukeParser parser = new DukeParser(contact, history, storage, ui);
        String[] tokens = input.split("\\s");
        String keyword = tokens[0];
        switch (keyword) {
        case "bye":
            response = parser.byeCommand(tokens);
            break;
        case "list":
            response = parser.listCommand(tokens);
            break;
        case "mark": {
            response = parser.markCommand(tokens);
            break;
        }
        case "unmark": {
            response = parser.unmarkCommand(tokens);
            break;
        }
        case "todo": {
            response = parser.todoCommand(tokens);
            break;
        }
        case "deadline": {
            response = parser.deadlineCommand(tokens);
            break;
        }
        case "event": {
            response = parser.eventCommand(tokens);
            break;
        }
        case "delete": {
            response = parser.deleteCommand(tokens);
            break;
        }
        case "update": {
            response = parser.updateCommand(tokens);
            break;
        }
        case "find": {
            response = parser.findCommand(tokens);
            break;
        }
        case "listcontacts": {
            response = parser.listcontactsCommand(tokens);
            break;
        }
        case "addcontacts": {
            response = parser.addcontactsCommand(tokens);
            break;
        }
        case "deletecontacts": {
            response = parser.deletecontactsCommand(tokens);
            break;
        }
        default:
            try {
                throw new MissingFormatArgumentException("invalid keywords");
            } catch (MissingFormatArgumentException ex) {
                response = ui.printListOfCommands();
                break;
            }
        }
        return getResponse();
    }

    /**
     * Method that gets DukeLCH's response to the user's input.
     * @return DukeLCH's response.
     */
    public String getResponse() {
        return response;
    }
}
