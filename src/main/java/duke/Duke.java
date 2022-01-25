package duke;

import duke.commands.CommandManager;

public class Duke {
    public static void main(String[] args) {
        CommandManager commander = new CommandManager();
        commander.run();
    }
}
