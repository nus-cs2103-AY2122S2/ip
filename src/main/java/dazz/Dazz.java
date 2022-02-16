package dazz;

import dazz.command.Command;
import dazz.exception.DazzException;
import dazz.exception.ErrorType;

/**
 * Represents a chatbot that allows user to keep track of their tasks (i.e a to-do list).
 */
public class Dazz {
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Creates Dazz.
     */
    public Dazz() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadList());
        this.ui = new Ui();
        CommandMapper.loadExistingMapping();
    }

    /**
     * Runs Dazz, allowing it to receive user inputs.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DazzException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                // in case user did not type a number for
                // delete, mark, unmark commands
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Gets the response of Dazz based on user input.
     * @param input The user input.
     * @return Response of Dazz based on the user input.
     */
    public String getResponse(String input) {
        try {
            Command responseCommand = Parser.parse(input);
            String response = responseCommand.execute(taskList, ui, storage);
            return response;
        } catch (DazzException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return ErrorType.INVALID_INDEX.getErrorMessage();
        }
    }

    public static void main(String[] args) {
        new Dazz().run();
    }
}
