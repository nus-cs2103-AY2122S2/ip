package juke.command;

import juke.task.TaskStatus;
import juke.task.TaskType;

import java.util.EnumSet;
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
        COMMANDS.put("bye", () -> new ExitCommand());
        COMMANDS.put("list", () -> new ListCommand());
        EnumSet.allOf(TaskStatus.class)
                .forEach(status -> COMMANDS.put(status.getCommandName(), () -> new MarkCommand(status)));
        EnumSet.allOf(TaskType.class)
                .forEach(type -> COMMANDS.put(type.getCommandName(), () -> new AddCommand(type)));
        COMMANDS.put("delete", () -> new DeleteCommand());
        COMMANDS.put("find", () -> new FindCommand());
    }
}
