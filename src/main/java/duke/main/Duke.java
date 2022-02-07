package duke.main;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Duke is a chat-bot that curates a todo list according to user's commands.
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    private static final String INDEX_OUT_OF_BOUND_MESSAGE = "You have no task with that number.\n";
    private static final String DATE_PARSE_ERROR = "Your date and times have not been formatted properly.\n";
    /**
     * Constructor for Duke class
     * @param filePath Path for file where data is stored and loaded
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return INDEX_OUT_OF_BOUND_MESSAGE;
        } catch (DateTimeParseException e) {
            return DATE_PARSE_ERROR;
        }
    }

}
