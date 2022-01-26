package duke;

import duke.commands.CommandManager;

/**
 * A Chatbot that can be used as a digital scheduler.
 */
public class Duke {
    public static void main(String[] args) {
        CommandManager commander = new CommandManager();
        commander.run();
    }
}
