package duke;

import java.io.IOException;
import java.util.MissingFormatArgumentException;

/**
 * Represents the Duke class that serves as the Main program for DukeLCH.
 */
public class Duke {

    private DukeHistory history;
    private DukeStorage storage;
    private DukeUi ui;
    private String response;

    /**
     * Constructor for Duke class that takes in a filePath to load data from the users local hard disk.
     *
     * @param filePath A String representing a given filePath.
     */
    public Duke(String filePath) {
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
     * Method that starts the Duke Main Program.
     * @return DukeLCH's response.
     */
    public String run(String input) {
        DukeParser parser = new DukeParser(history, storage, ui);
        String[] tokens = input.split("\\s");
        String keyword = tokens[0];
        switch (keyword) {
        case "bye":
            response = parser.byeCommand(tokens);
        case "list":
            response = parser.listCommand(tokens);
        case "mark": {
            response = parser.markCommand(tokens);
        }
        case "unmark": {
            response = parser.unmarkCommand(tokens);
        }
        case "todo": {
            response = parser.todoCommand(tokens);
        }
        case "deadline": {
            response = parser.deadlineCommand(tokens);
        }
        case "event": {
            try {
                if (tokens.length <= 1) {
                    throw new MissingFormatArgumentException("no argument detected");
                }
                response = cmd.event(tokens, history);
                storage.update(history);
                break;
            } catch (MissingFormatArgumentException ex) {
                response = ui.printMissingArgumentError(keyword);
                break;
            } catch (DukeException ex) {
                response = ui.printMissingDateTimeArgumentError(keyword);
                break;
            } catch (IOException e) {
                response = ui.printWriteError();
                break;
            }
        }
        case "delete": {
            try {
                int index = Integer.parseInt(tokens[1]);
                response = cmd.delete(index - 1, history);
                storage.update(history);
                break;
            } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException ex) {
                response = ui.printInvalidArgumentError();
                break;
            } catch (IOException e) {
                response = ui.printWriteError();
                break;
            }
        }
        case "update": {
            try {
                storage.update(history);
                response = "";
                break;
            } catch (IOException e) {
                response = ui.printWriteError();
                break;
            }
        }
        case "find": {
            try {
                if (tokens.length <= 1) {
                    throw new MissingFormatArgumentException("no argument detected");
                }
                response = cmd.find(tokens, history);
                break;
            } catch (MissingFormatArgumentException ex) {
                response = ui.printMissingArgumentError(keyword);
                break;
            } catch (DukeException e) {
                response = ui.printFoundNothing();
                break;
            }
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
     *
     * @return DukeLCH's response.
     */
    public String getResponse() {
        return response;
    }
}
