package duke;

import java.io.ByteArrayOutputStream;

import bot.Bot;
import parser.Parser;
import storage.Storage;
import tasklist.StorageTaskList;
import ui.Ui;

/**
 * Represents an interactive Duke chatbot.
 */
public class Duke {
    private final Bot duke;
    private final ByteArrayOutputStream output;

    /**
     * Returns a Duke chatbot that can respond to user queries.
     *
     * @param appPath the directory to store the Duke chatbot's data.
     * @throws Exception If any operation related to storage or UI fails.
     */
    public Duke(String appPath) throws Exception {
        this.output = new ByteArrayOutputStream();
        this.duke = new Bot(
                new Parser(),
                new Ui(System.in, this.output, false),
                new StorageTaskList(new Storage(appPath)));
    }

    /**
     * Processes a user's query.
     *
     * @param query the instruction for the Duke chatbot to process.
     * @return The Duke chatbot's response to the given user query.
     */
    public DukeResponse processQuery(String query) {
        final boolean isExit = this.duke.execute(query);
        final String response = this.output.toString();
        this.output.reset();
        return new DukeResponse(response, isExit);
    }

}
