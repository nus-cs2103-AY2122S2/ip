package chatbot.command;

import chatbot.sfx.Sfx;
import chatbot.task.TaskList;

public class HelloCommand extends Command {
    public static final String TRIGGER = "hello";
    public static final String FORMAT = TRIGGER;

    public HelloCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        validateTrigger(input);

        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\nCommand format: " + FORMAT, Sfx.SFX_ERROR_INVALID_ARGS);
        }
        return new CommandOutput("Hey there!\nType \"help\" for a list of commands.", Sfx.SFX_COMMAND_HELLO);
    }
}
