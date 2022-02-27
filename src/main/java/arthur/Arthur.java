package arthur;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import arthur.exceptions.ArthurException;
import arthur.exceptions.EmptyDescriptionException;
import arthur.exceptions.InvalidInstructionException;
import arthur.exceptions.InvalidStoredDataFormat;

public class Arthur {
    private static final String DATE_TIME_ERROR_MESSAGE = "Please enter the date/time in format: "
            + "yyyy-mm-dd HH:mm \n" + "You can also enter time or date only";
    private static final String VALID_NUM_ERROR = "Please enter a valid number!";
    private static final String IO_ISSUE_MESSAGE = "Sorry, there seems to be an Issue. \n"
            + "Please restart and try again";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "The file can't be found. \n"
            + "Please restart and try again.";
    private final Storage storage;
    private TaskList tasks;

    /**
     * Initiates the arthur.Ui, arthur.Storage and taskList object.
     * Adds existing tasks from hard disk if any available.
     */
    public Arthur() {
        Ui ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage);
        } catch (DateTimeParseException e) {
            System.out.println(DATE_TIME_ERROR_MESSAGE);
        } catch (InvalidStoredDataFormat f) {
            f.getMessage();
        }
    }
    /**
     * Runs the given instructions.
     * @param string The user input command.
     * @return A string output to be displayed to the user.
     */
    public String run(String string) {
        String result;
        try {
            ArthurException.checkException(string);
            Parser commander = new Parser(string);
            result = commander.execute(tasks, storage);
        } catch (DateTimeParseException e) {
            result = DATE_TIME_ERROR_MESSAGE;
        } catch (InvalidInstructionException | EmptyDescriptionException f) {
            result = f.getMessage();
        } catch (IndexOutOfBoundsException g) {
            result = VALID_NUM_ERROR;
        } catch (FileNotFoundException h) {
            result = FILE_NOT_FOUND_ERROR_MESSAGE;
        } catch (IOException i) {
            result = IO_ISSUE_MESSAGE;
        }
        return result;
    }
}
