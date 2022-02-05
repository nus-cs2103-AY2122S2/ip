package duke;

import java.io.IOException;
import java.util.MissingFormatArgumentException;

/**
 * Represents the Duke class that serves as the Main program for DukeLCH.
 */
public class Duke {

    private Commands cmd;
    private DukeUi ui;
    private DukeStorage storage;
    private DukeHistory history;
    private String response;

    /**
     * Constructor for Duke class that takes in a filePath to load data from the users local hard disk.
     *
     * @param filePath A String representing a given filePath.
     */
    public Duke(String filePath) {
        cmd = new Commands();
        ui = new DukeUi();
        storage = new DukeStorage();
        history = new DukeHistory();

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
        //ui.printGreeting();
        String[] tokens = input.split("\\s");
        String keyword = tokens[0];
        switch (keyword) {
        case "bye":
            try {
                if (tokens.length != 1) {
                    throw new DukeException("argument for bye detected");
                }
                response = cmd.bye();
                storage.update(history);
                break;
            } catch (DukeException ex) {
                response = ui.printFoundArgumentError();
                break;
            } catch (IOException e) {
                response = ui.printWriteError();
                break;
            }
        case "list":
            try {
                if (tokens.length != 1) {
                    throw new DukeException("argument for list detected");
                }
                response = cmd.list(history);
                break;
            } catch (DukeException ex) {
                response = ui.printFoundArgumentError();
                break;
            }
        case "mark": {
            try {
                int index = Integer.parseInt(tokens[1]);
                response = cmd.mark(index - 1, history);
                storage.update(history);
                break;
            } catch (IndexOutOfBoundsException ex) {
                response = ui.printInvalidArgumentError();
                break;
            } catch (IOException e) {
                response = ui.printWriteError();
                break;
            }
        }
        case "unmark": {
            try {
                int index = Integer.parseInt(tokens[1]);
                response = cmd.unmark(index - 1, history);
                storage.update(history);
                break;
            } catch (IndexOutOfBoundsException ex) {
                response = ui.printInvalidArgumentError();
                break;
            } catch (IOException e) {
                response = ui.printWriteError();
                break;
            }
        }
        case "todo": {
            try {
                if (tokens.length <= 1) {
                    throw new MissingFormatArgumentException("no argument detected");
                }
                response = cmd.todo(tokens, history);
                storage.update(history);
                break;
            } catch (MissingFormatArgumentException ex) {
                response = ui.printMissingArgumentError(keyword);
                break;
            } catch (IOException e) {
                response = ui.printWriteError();
                break;
            }
        }
        case "deadline": {
            try {
                if (tokens.length <= 1) {
                    throw new MissingFormatArgumentException("no argument detected");
                }
                response = cmd.deadline(tokens, history);
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
