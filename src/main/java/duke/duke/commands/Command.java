package duke.commands;

import duke.info.exception.InvalidInputException;
import duke.info.task.Calendar;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {

    /**
     * Constructs an empty Command
     */
    Command() {}

    /**
     * Returns true if and only if the Command is an instance of
     * ExitCommand
     * @return - true if the command is an ExitCommand
     */
    public boolean isExit() {
        return (this instanceof ExitCommand);
    }

    /**
     * Runs the execute command specific to the command type. This command will be overridden
     * in child classes to specify the appropriate execute functionality
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     * @throws InvalidInputException - if the input to the subclass commands are invalid
     */
    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        /* To be overridden in child classes; */
    }
}
