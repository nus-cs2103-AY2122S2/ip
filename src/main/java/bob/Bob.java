package bob;

import bob.command.ListCommand;
import bob.exception.BobException;
import bob.command.ByeCommand;
import bob.command.Command;

/**
 * The Bob class implements a chat-bot that records your tasks and tracks your progress.
 *
 * @author Ryan Low
 * @version 1.0
 */
class Bob {
    public Storage store;
    public Ui ui;
    private TaskList tasks;

    public static void main(String[] args) {
        new Bob().initializeBob();
    }

    /**
     * Represents the initializer of the Bob program.
     * Sets up the store, tasks, and ui for the program.
     */
    public void initializeBob() {
        store = new Storage("Bob.txt");
        tasks = new TaskList(store.getSavedStore());
        ui = new Ui();
        greet();
        activeListener();
    }

    /**
     * Prints the greeting messages for Bob upon initializing.
     */
    public void greet() {
        ui.logo();
        ui.greetMessage();
        new ListCommand(true).execute(tasks, ui, store);
        ui.userReply();
    }

    /**
     * Runs the program by actively listening to commands and executes them.
     */
    public void activeListener() {
        boolean isBye = false;
        while(!isBye) {
            String input = ui.readInput();
            if (input.isBlank()) {
                ui.badReply();
            } else {
                ui.line();
                try {
                    Command c = Parser.parse(input);
                    isBye = c instanceof ByeCommand;
                    c.execute(tasks, ui, store);
                } catch (BobException e) {
                    ui.showError(e.getBobReply());
                }
            }
            if (!isBye) {
                ui.userReply();
            }
        }
    }
}
