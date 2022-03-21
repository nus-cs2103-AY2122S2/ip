package gary;
import gary.exception.GaryException;
import gary.ui.Parser;
import gary.ui.Storage;
import gary.ui.TaskList;

/**
 * Represents a chat-bot that manages your daily to-do list
 */
public class Gary {

    private static Storage storage;
    protected static TaskList tasks;
    protected static Parser parser;

    /**
     * Initializes Storage, TaskList, Ui and Parser required
     *
     * @param filePath file path to file containing history of chat-bot
     */
    public Gary(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.loadFile());
    }

    /**
     * Function to generate Gary's response to user input
     *
     * @param input user input
     * @return Gary's response
     */
    protected String getResponse(String input) {
        String resp;
        try {
            resp = parser.parse(input, tasks);
        } catch (GaryException e) {
            return e.garyError();
        } catch (AssertionError e) {
            return "Sorry... What do you need to do again?";
        }
        return resp;
    }
}