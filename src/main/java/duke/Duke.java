package duke;

import duke.commands.CommandManager;

/**
 * A Chatbot that can be used as a digital scheduler.
 */
public class Duke {

    /**
     * Creates the CommandManager Object and runs the program.
     *
     * @param args String array containing arguments for main method
     */
    public static void main(String[] args) {
        CommandManager commander = new CommandManager();
        commander.run();
    }
}
