package duke.managers;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.StoreDeadlineCommand;
import duke.commands.StoreEventCommand;
import duke.commands.StoreTodoCommand;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents the manager of the entire Duke program.
 * Stores all the required instances of the program which includes
 * the storage, task list and ui.
 */
public class DukeManager {

    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;
    protected Parser parser;

    /**
     * Creates an instance of DukeManager.
     *
     * @param storagePath the relative path to the storage file
     *                    used for the program.
     */
    public DukeManager(String storagePath) {
        storage = new Storage(storagePath);
        ui = new Ui();
        parser = new Parser();

        initializeCommands();
        try {
            taskList = storage.load();
        } catch (DukeException e) {
            taskList = new TaskList();
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Initializes all the commands that should be recognized by Duke.
     * Further extensions in regard to new commands should be done here.
     */
    protected void initializeCommands() {
        parser.addCommand(new DeleteCommand());
        parser.addCommand(new ExitCommand());
        parser.addCommand(new FindCommand());
        parser.addCommand(new ListCommand());
        parser.addCommand(new MarkCommand(true));
        parser.addCommand(new MarkCommand(false));
        parser.addCommand(new StoreDeadlineCommand());
        parser.addCommand(new StoreEventCommand());
        parser.addCommand(new StoreTodoCommand());
    }

    /**
     * Executes the program loop of Duke.
     */
    public void run() {
        ui.greet();
        boolean exited = false;
        while (!exited) {
            try {
                String input = ui.getInput();
                ui.printLine();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
                exited = command.getExit();
            } catch (DukeException exception) {
                ui.showMessage(exception.getMessage());
            } finally {
                ui.printLine();
            }
        }
        ui.close();
    }


}
