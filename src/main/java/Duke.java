import duke.command.Command;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A text based chatbot able to keep track of tasks.
 */
public class Duke {
    public boolean isListening = true;
    private final String FILE_PATH;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main constructor of Duke class.
     *
     * @param filePath file path to store saved task list.
     */
    public Duke(String filePath) {
        this.FILE_PATH = filePath;
        this.storage = new Storage(FILE_PATH);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
    }


    /**
     * Parses user input and executes the appropriate command.
     *
     * @param input user instruction to be executed.
     */
    public void run(String input) {

        try {
            Command c = Parser.parse(input);
            ui.showLine();
            c.execute(tasks, ui, storage);
            isListening = !c.isExit();
            ui.showLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.notEnoughFieldsMessage();
        } catch (NumberFormatException e) {
            ui.invalidIndex();
        } catch (DateTimeParseException e) {
            ui.invalidDate();
        }
    }

    /**
     * Runs the main program.
     *
     * @param args inputs to the program.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke chatbot = new Duke("data/data.bin");
        chatbot.ui.greet();
        while (chatbot.isListening) {
            chatbot.run(sc.nextLine());
        }
    }
}
