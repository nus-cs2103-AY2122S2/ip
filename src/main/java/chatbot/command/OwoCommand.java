package chatbot.command;

import chatbot.sfx.Sfx;
import chatbot.task.TaskList;

public class OwoCommand extends Command {
    public static final String TRIGGER = "owo";
    public static final String FORMAT = TRIGGER;

    public OwoCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\nCommand format: " + FORMAT, Sfx.SFX_ERROR_INVALID_ARGS);
        }
        return new CommandOutput("(。O⁄ ⁄ω⁄ ⁄ O。)", Sfx.SFX_COMMAND_OWO);
    }
}
