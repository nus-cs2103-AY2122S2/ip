package juke.command;

import java.util.HashMap;
import java.util.function.Supplier;

public class CommandHandler {
    public static final HashMap<String, Supplier<? extends Command>> COMMANDS = new HashMap<>();
    
    public static void registerCommands() {
        COMMANDS.put("echo", () -> new EchoCommand());
    }
}
