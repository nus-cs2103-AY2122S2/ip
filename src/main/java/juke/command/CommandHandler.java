package juke.command;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Handles command initialization and an interface for interacting with commands.
 */
public class CommandHandler {
    /**
     * Collection of command objects mapped to its command name.
     */
    public static final HashMap<String, Supplier<? extends Command>> COMMANDS = new HashMap<>();
    
    /**
     * Initializes the commands.
     */
    public static void registerCommands() {
        COMMANDS.put("echo", () -> new EchoCommand());
    }
}
