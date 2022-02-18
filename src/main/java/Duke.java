import bot.Bot;
import parser.Parser;
import storage.Storage;
import tasklist.StorageTaskList;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents the entire chatbot program.
 */
public class Duke {
    private static final String APP_PATH = "/test";

    /**
     * Represents the main entry point to start the chatbot.
     *
     * @param args contains the initial command line arguments that are supplied by the user.
     * @throws Exception If an unhandled or unexpected exception occurs.
     */
    public static void main(String[] args) throws Exception {
        final Parser parser = new Parser();
        final Ui ui = new Ui(System.in, System.out);
        final TaskList taskList = new StorageTaskList(new Storage(Duke.APP_PATH));

        final Bot bot = new Bot(parser, ui, taskList);
        bot.run();
    }
}
