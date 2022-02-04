package arthur;

import java.time.format.DateTimeParseException;

import arthur.exceptions.ArthurException;
import arthur.exceptions.EmptyDescriptionException;
import arthur.exceptions.InvalidInstructionException;

public class Arthur {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initiates the arthur.Ui, arthur.Storage and taskList object.
     * Adds existing tasks from hard disk if any available.
     */
    public Arthur() {
        ui = new Ui();
        storage = new Storage(ui);
        try {
            tasks = new TaskList(storage);
        } catch (DateTimeParseException e) {
            ui.printFormat("Please enter the date/time in format: yyyy-mm-dd hh:mm \n"
                    + "You can also enter time or date only");
        }
    }

    /**
     * Runs the given instructions till "bye" command is given
     */
    public String run(String string) {
        ui.showWelcome();
        boolean isExit = false;
        String result = string;
        while (!isExit) {
            String inst = string;
            try {
                ArthurException.checkException(inst);
                Parser commander = new Parser(inst);
                result = commander.execute(tasks, storage, ui);
                isExit = true;
            } catch (DateTimeParseException e) {
                ui.printFormat("Please enter the date/time in format: yyyy-mm-dd hh:mm \n"
                        + "You can also enter time or date only");
            } catch (InvalidInstructionException | EmptyDescriptionException f) {
                ui.printFormat(f.getMessage());
            }
        }
        return result;
    }
}
