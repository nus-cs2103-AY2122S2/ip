package duke.internal;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.SortCommand;
import duke.commands.StoreDeadlineCommand;
import duke.commands.StoreEventCommand;
import duke.commands.StoreTodoCommand;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.GuiFeedback;

/**
 * Represents the manager of the entire Duke program.
 * Stores all the required instances of the program which includes
 * the storage, task list and ui.
 */
public class Duke {

    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;
    protected Parser parser;

    /**
     * Creates an instance of Duke and all its internal managers.
     *
     * @param storagePath the relative path to the storage file
     *                    used for the program.
     */
    public Duke(String storagePath) {
        storage = new Storage(storagePath);
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Initializes the duke system and internal managers for the duke program to be functional.
     * @throws DukeException when the initialization causes some internal error.
     */
    public String initialize() {
        initializeCommands();
        boolean isFirstTime = false;
        try {
            taskList = storage.load();
        } catch (DukeException e) {
            taskList = new TaskList();
            isFirstTime = true;
        }
        if (isFirstTime) {
            String output = "Hi i see it's your first time here!" + "\n";
            try {
                output += parser.parse("help").execute(taskList);
            } catch (DukeException ex) {
                //This exception should never be thrown as help is an existing command.
            }
            return ui.getFormattedMessage(output);
        }

        return ui.getGreet();
    }

    public GuiFeedback getResponse(String input) {
        assert parser != null : "Parser must be initialized.";
        assert storage != null : "Storage must be initialized.";
        try {
            Command command = parser.parse(input);
            String output = command.execute(taskList);
            if (command.getModify()) {
                storage.save(taskList);
            }
            return new GuiFeedback(ui.getFormattedMessage(output), command.getExit());
        } catch (DukeException e) {
            return new GuiFeedback(ui.getFormattedMessage(e.getMessage()), false);
        }
    }

    /**
     * Initializes all the commands are recognized by Duke.
     * Further extensions in regard to new commands should be done here.
     */
    protected void initializeCommands() {
        assert parser != null : "Parser must be initialized.";
        parser.addCommand(new DeleteCommand());
        parser.addCommand(new ExitCommand());
        parser.addCommand(new FindCommand());
        parser.addCommand(new ListCommand());
        parser.addCommand(new MarkCommand(true));
        parser.addCommand(new MarkCommand(false));
        parser.addCommand(new StoreDeadlineCommand());
        parser.addCommand(new StoreEventCommand());
        parser.addCommand(new StoreTodoCommand());
        parser.addCommand(new HelpCommand());
        parser.addCommand(new SortCommand());
    }
}
