package chatbot.util;

import chatbot.command.Command;
import chatbot.command.CommandOutput;
import chatbot.task.TaskList;

import java.util.Hashtable;

/**
 * Parser of user input.
 */
public class Parser {
    private final Hashtable<String, Command> commands;
    private final Command unrecognizedCommand;

    /**
     * Constructs an empty parser.
     */
    public Parser() {
        this.commands = new Hashtable<>();
        this.unrecognizedCommand = new Command() {
            @Override
            public CommandOutput execute(String[] input, TaskList taskList) {
                return new CommandOutput("Unrecognised command.", "/audio/ding.wav");
            }
        };
    }

    /**
     * Associates the specified trigger with the specified command in this map.
     * If the parser previously contained a mapping for the trigger, the old command is replaced.
     * @param trigger trigger with which the specified command is to be associated
     * @param command command to be associated with the specified trigger
     */
    public void addCommand(String trigger, Command command) {
        this.commands.put(trigger, command);
    }

    /**
     * Returns the command to which the specified trigger is mapped, or a default command if this parser contains no mapping for the trigger.
     * @param trigger the trigger whose associated command is to be returned
     * @return the command to which the specified trigger is mapped, or a default command if this parser contains no mapping for the trigger.
     */
    public Command getCommand(String trigger) {
        return commands.getOrDefault(trigger, unrecognizedCommand);
    }
}