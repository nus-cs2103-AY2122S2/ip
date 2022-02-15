package taskie;

import taskie.command.Command;
import taskie.parser.Parser;
import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * A text based chatbot able to keep track of tasks.
 */
public class Taskie {
    public boolean isListening = true;
    private final String FILE_PATH;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main constructor of Taskie class.
     *
     * @param filePath file path to store saved task list.
     */
    public Taskie(String filePath) {
        this.FILE_PATH = filePath;
        this.storage = new Storage(FILE_PATH);
        this.tasks = new TaskList(storage.load());
        assert tasks != null;
        this.ui = new Ui();
    }


    /**
     * Parses user input and executes the appropriate command.
     *
     * @param input user instruction to be executed.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            Command c = Parser.parse(input);
            assert c != null;
            c.execute(tasks, ui, storage, response);
            isListening = !c.isExit();
        } catch (ArrayIndexOutOfBoundsException e) {
            response.append(ui.notEnoughFieldsMessage());
        } catch (NumberFormatException e) {
            response.append(ui.invalidIndex());
        } catch (DateTimeParseException e) {
            response.append(ui.invalidDate());
        }
        return response.toString();
    }

    public String greet() {
        return ui.greet();
    }
}
