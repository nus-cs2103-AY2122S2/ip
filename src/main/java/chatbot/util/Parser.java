package chatbot.util;

import chatbot.command.Command;
import chatbot.command.CommandOutput;
import chatbot.task.TaskList;

import java.util.Hashtable;

public class Parser {
    private final Hashtable<String, Command> commands;
    private final Command unrecognizedCommand;

    public Parser() {
        this.commands = new Hashtable<>();
        this.unrecognizedCommand = new Command() {
            @Override public CommandOutput execute(String[] input, TaskList taskList) {
                return new CommandOutput("Unrecognised command.", "audio/ding.wav");
            }
        };

    }

    public void addCommand(String keyword, Command command) {
        this.commands.put(keyword, command);
    }

    public Command getCommand(String keyword) {
        return commands.getOrDefault(keyword, unrecognizedCommand);
    }
}